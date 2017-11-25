#pragma once

#include<vector>

/*
Generic matrix class
exported as dll
*/
template <class T>
class Matrix {
private:
	std::vector<std::vector<T>> data;
	int rows;
	int cols;
public:
	__declspec(dllexport) Matrix() = delete;
	__declspec(dllexport) Matrix(int rows, int cols);
	__declspec(dllexport) ~Matrix() = default;

	__declspec(dllexport) T getElement(int i, int j);
	__declspec(dllexport) void setElement(int i, int j, T element);
	__declspec(dllexport) int getRows();
	__declspec(dllexport) int getCols();
	__declspec(dllexport) void setData(std::vector<std::vector<T>> data);

	__declspec(dllexport) bool operator==(const Matrix& other) const;
};

template<class T>
inline Matrix<T>::Matrix(int rows, int cols):
	rows{rows},
	cols{cols},
	data(rows, std::vector<T>(cols)) // rows elements of vector<T> type which has cols elements
{
}

template<class T>
inline  T Matrix<T>::getElement(int i, int j)
{
	return data[i][j];
}

template<class T>
inline  void Matrix<T>::setElement(int i, int j, T element)
{
	data[i][j] = element;
}

template<class T>
inline  int Matrix<T>::getRows()
{
	return rows;
}

template<class T>
inline  int Matrix<T>::getCols()
{
	return cols;
}

template<class T>
inline void Matrix<T>::setData(std::vector<std::vector<T>> data)
{
	this->data = data;
}

template<class T>
inline bool Matrix<T>::operator==(const Matrix & other) const
{
	if (rows != other.rows) {
		return false;
	}
	if (cols != other.cols) {
		return false;
	}
	for (int i = 0; i < rows; i++) {
		for (int j = 0; j < cols; j++) {
			if (data[i][j] != other.data[i][j]) {
				return false;
			}
		}
	}
	return true;
}



