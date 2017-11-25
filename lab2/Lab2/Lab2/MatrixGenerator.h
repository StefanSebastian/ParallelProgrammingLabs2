#pragma once

#include "Matrix.h"
#include "ComplexNumber.h"

/*
Generates random matrixes of given size
*/
class __declspec(dllexport) MatrixGenerator {
private:
	double getRandomInRange(double start, double end);
public:
	Matrix<ComplexNumber> getRandomComplexNumberMatrix(int rows, int cols);
	Matrix<double> getRandomDoubleMatrix(int rows, int cols);
	Matrix<int> getRandomIntMatrix(int rows, int cols);
};