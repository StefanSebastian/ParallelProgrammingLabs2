#include "MatrixGenerator.h"
#include <random>

double MatrixGenerator::getRandomInRange(double start, double end)
{
	std::random_device rd;
	std::mt19937 mt(rd());
	std::uniform_real_distribution<double> dist(start, end);
	return dist(mt);
}

Matrix<ComplexNumber> MatrixGenerator::getRandomComplexNumberMatrix(int rows, int cols)
{
	Matrix<ComplexNumber> res = Matrix<ComplexNumber>(rows, cols);

	for (int i = 0; i < rows; i++) {
		for (int j = 0; j < cols; j++) {
			double a = getRandomInRange(1, 10);
			double b = getRandomInRange(1, 10);
			double sign = getRandomInRange(0, 1);
			if (sign > 0.5) {
				res.setElement(i, j, ComplexNumber(a, b));
			}
			else {
				res.setElement(i, j, ComplexNumber(a, -b));
			}
			
		}
	}

	return res;
}

Matrix<double> MatrixGenerator::getRandomDoubleMatrix(int rows, int cols)
{
	Matrix<double> res = Matrix<double>(rows, cols);

	for (int i = 0; i < rows; i++) {
		for (int j = 0; j < cols; j++) {
			res.setElement(i, j, getRandomInRange(1, 10));
		}
	}

	return res;
}

Matrix<int> MatrixGenerator::getRandomIntMatrix(int rows, int cols)
{
	Matrix<int> res = Matrix<int>(rows, cols);

	for (int i = 0; i < rows; i++) {
		for (int j = 0; j < cols; j++) {
			res.setElement(i, j, (int)getRandomInRange(1, 10));
		}
	}

	return res;
}
