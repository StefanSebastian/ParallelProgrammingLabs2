#pragma once

#include "Matrix.h"

/*
Defines a set of generic matrix operations
*/
template <class T>
class __declspec(dllexport) MatrixOperations {
public:
	// generic functions 
	static T addition(Matrix<T>& m1, Matrix<T>& m2, int i, int j);

	static T matrixMultiplication(Matrix<T>& m1, Matrix<T>& m2, int idx1, int idx2);

	static T pointOperator(Matrix<T>& m1, Matrix<T>& m2, int idx1, int idx2);

	static T multiply(Matrix<T>& m1, Matrix<T>& m2, int idx1, int idx2);

	// only for testing purposes 
	static int pointIntOperator(Matrix<int>& m1, Matrix<int>& m2, int idx1, int idx2);

	static ComplexNumber pointComplexNrOperator(Matrix<ComplexNumber>& m1, Matrix<ComplexNumber>& m2, int idx1, int idx2);
};

template<class T>
inline T MatrixOperations<T>::addition(Matrix<T>& m1, Matrix<T>& m2, int i, int j)
{
	if (m1.getRows() != m2.getRows() || m1.getCols() != m2.getCols()) {
		std::cout << "Invalid input";
	}
	return m1.getElement(i, j) + m2.getElement(i, j);
}

template<class T>
inline T MatrixOperations<T>::matrixMultiplication(Matrix<T>& m1, Matrix<T>& m2, int idx1, int idx2)
{
	if (m1.getCols() != m2.getRows()) {
		std::cout << "Invalid input";
	}
	T product = T();
	for (int i = 0; i < m1.getCols(); i++) {
		product = product + m1.getElement(idx1, i) * m2.getElement(i, idx2);
	}
	return product;
}

template<class T>
inline T MatrixOperations<T>::pointOperator(Matrix<T>& m1, Matrix<T>& m2, int idx1, int idx2)
{
	T one { 1 };
	T a = m1.getElement(idx1, idx2);
	T b = m2.getElement(idx1, idx2);
	return one / (one / a + one / b);
}

template<class T>
inline T MatrixOperations<T>::multiply(Matrix<T>& m1, Matrix<T>& m2, int idx1, int idx2)
{
	return m1.getElement(idx1, idx2) * m2.getElement(idx1, idx2);
}

template<class T>
inline int MatrixOperations<T>::pointIntOperator(Matrix<int>& m1, Matrix<int>& m2, int idx1, int idx2)
{
	if (m1.getRows() != m2.getRows() || m1.getCols() != m2.getCols()) {
		std::cout << "Invalid input";
	}
	int a = m1.getElement(idx1, idx2);
	int b = m2.getElement(idx1, idx2);
	double aux = (1 / (double)a) + (1 / (double)b);
	int res = (int)(1 / aux);
	return res;
}

template<class T>
inline ComplexNumber MatrixOperations<T>::pointComplexNrOperator(Matrix<ComplexNumber>& m1, Matrix<ComplexNumber>& m2, int idx1, int idx2)
{
	if (m1.getRows() != m2.getRows() || m1.getCols() != m2.getCols()) {
		std::cout << "Invalid input";
	}
	ComplexNumber a = m1.getElement(idx1, idx2);
	ComplexNumber b = m2.getElement(idx1, idx2);
	ComplexNumber one = ComplexNumber(1);
	return one / (one / a + one / b);
}


