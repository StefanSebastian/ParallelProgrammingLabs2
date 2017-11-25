#pragma once
#include<iostream>
#include<thread>
#include<chrono>
#include "Matrix.h"

/*
splits a matrix and assigns parallel workers for each partition
every worker applies a given operation for each cell of the result matrix
*/
template <class T>
class  ParallelCalculator {
private:
	/*
	worker method - called by each thread
	*/
	__declspec(dllexport) static void worker(Matrix<T>& m1, Matrix<T>& m2, Matrix<T>& result, int start, int end,
		std::function<T(Matrix<T>&, Matrix<T>&, int, int)> operatorFunction);
public:
	/*
	splits a matrix and assigns workers
	*/
	__declspec(dllexport) static double calculate(Matrix<T>& m1, Matrix<T>& m2, Matrix<T>& result, int nrThreads,
		std::function<T(Matrix<T>&, Matrix<T>&, int, int)> operatorFunction);
};


template<class T>
inline void ParallelCalculator<T>::worker(
	Matrix<T>& m1, 
	Matrix<T>& m2, 
	Matrix<T>& result, 
	int start, 
	int end, 
	std::function<T(Matrix<T>&, Matrix<T>&, int, int)> operatorFunction)
{
	//Logger::WriteMessage(std::string("worker started").c_str());
	int cols = result.getCols();
	int startRow = start / cols;
	int startCol = start % cols;
	int endRow = end / cols;
	int endCol = end % cols;

	int i = startRow; int j = startCol;
	while (!(i == endRow && j == endCol)) {
		result.setElement(i, j, operatorFunction(m1, m2, i, j));

		if (j < cols - 1) {
			j++;
		}
		else {
			j = 0;
			i++;
		}
	}
}


template<class T>
inline double ParallelCalculator<T>::calculate(Matrix<T>& m1, Matrix<T>& m2, Matrix<T>& result, int nrThreads, std::function<T(Matrix<T>&, Matrix<T>&, int, int)> operatorFunction)
{
	
	int rows = result.getRows();
	int cols = result.getCols();
	int len = rows * cols;
	if (nrThreads < 1 || nrThreads > len) {
		std::cout << "Invalid number of threads";
		return -1;
	}
	
	// timestamp
	auto startTime = std::chrono::high_resolution_clock::now();

	// thread operations 
	int elemPerThread = len / nrThreads;
	int extraElems = len % nrThreads;
	int start = 0; int end = elemPerThread;

	std::vector<std::thread> threads(nrThreads);
	for (int i = 0; i < nrThreads; i++) {
		if (i < extraElems) {
			end++; // consider the extra elements
		}

		threads[i] = std::thread(
			ParallelCalculator<T>::worker,
			std::ref(m1), 
			std::ref(m2), 
			std::ref(result), 
			start, 
			end, 
			operatorFunction
		);

		start = end;
		end += elemPerThread;
	}

	for (int i = 0; i < nrThreads; i++) {
		threads[i].join();
	}

	// ending timestamp
	auto duration = std::chrono::high_resolution_clock::now() - startTime;
	double time = std::chrono::duration<double, std::milli>(duration).count(); 
	//Logger::WriteMessage((std::to_string(time) + " for " + std::to_string(nrThreads) + "threads").c_str());
	return time;
}
