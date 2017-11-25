#pragma once

#include"Matrix.h"
#include"ComplexNumber.h"

#include<fstream>

/*
A set of util function for matrixes
*/
class __declspec(dllexport) MatrixUtils {
public:
	/*
	prints an int matrix
	*/
	static std::string printIntMatrix(Matrix<int> m);
	/*
	prints a complex matrix
	*/
	static std::string printComplexMatrix(Matrix<ComplexNumber> m);
	/*
	reads an int matrix from a file
	*/
	static Matrix<int> readIntMatrix(std::string path);
	/*
	reads a complex matrix from a file
	*/
	static Matrix<ComplexNumber> readComplexNrMatrix(std::string path);
	/*
	reads a double matrix from a file
	*/
	static Matrix<double> readDoubleMatrix(std::string path);
	/*
	writes an int matrix to a file
	*/
	static void writeIntMatrix(std::string path, Matrix<int>& m);
	/*
	writes a complex matrix to a file
	*/
	static void writeComplexNrMatrix(std::string path, Matrix<ComplexNumber>& m);
	/*
	writes a double matrix to a file
	*/
	static void writeDoubleMatrix(std::string path, Matrix<double>& m);
	/*
	parses a complex number from a string
	*/
	static ComplexNumber parseComplexNumber(std::string number);
};