#include "MatrixUtils.h"

std::string MatrixUtils::printIntMatrix(Matrix<int> m)
{
	std::string res = "";
	for (int i = 0; i < m.getRows(); i++) {
		for (int j = 0; j < m.getCols(); j++) {
			res += m.getElement(i, j);
			res += " ";
		}
		res += "\n";
	}
	return res;
}

std::string MatrixUtils::printComplexMatrix(Matrix<ComplexNumber> m)
{
	std::string res = "";
	for (int i = 0; i < m.getRows(); i++) {
		for (int j = 0; j < m.getCols(); j++) {
			res += m.getElement(i, j).toString();
			res += " ";
		}
		res += "\n";
	}
	return res;
}

Matrix<int> MatrixUtils::readIntMatrix(std::string path)
{
	std::ifstream fin(path);
	int rows, cols;
	fin >> rows;
	fin >> cols;
	Matrix<int> matrix = Matrix<int>(rows, cols);
	for (int i = 0; i < rows; i++) {
		for (int j = 0; j < cols; j++) {
			int nr;
			fin >> nr;
			matrix.setElement(i, j, nr);
		}
	}
	return matrix;
}

Matrix<ComplexNumber> MatrixUtils::readComplexNrMatrix(std::string path)
{
	std::ifstream fin(path);
	int rows, cols;
	fin >> rows;
	fin >> cols;
	Matrix<ComplexNumber> matrix = Matrix<ComplexNumber>(rows, cols);
	for (int i = 0; i < rows; i++) {
		for (int j = 0; j < cols; j++) {
			std::string nr;
			fin >> nr;
			matrix.setElement(i, j, parseComplexNumber(nr));
		}
	}
	return matrix;
}

Matrix<double> MatrixUtils::readDoubleMatrix(std::string path)
{
	std::ifstream fin(path);
	int rows, cols;
	fin >> rows;
	fin >> cols;
	Matrix<double> matrix = Matrix<double>(rows, cols);
	for (int i = 0; i < rows; i++) {
		for (int j = 0; j < cols; j++) {
			double nr;
			fin >> nr;
			matrix.setElement(i, j, nr);
		}
	}
	return matrix;
}

void MatrixUtils::writeIntMatrix(std::string path, Matrix<int>& m)
{
	std::ofstream fout(path);
	fout << m.getRows() << " " << m.getCols() << "\n";
 	for (int i = 0; i < m.getRows(); i++) {
		for (int j = 0; j < m.getCols(); j++) {
			fout << m.getElement(i, j) << " ";
		}
		fout << "\n";
	}
}

void MatrixUtils::writeComplexNrMatrix(std::string path, Matrix<ComplexNumber>& m)
{
	std::ofstream fout(path);
	fout << m.getRows() << " " << m.getCols() << "\n";
	for (int i = 0; i < m.getRows(); i++) {
		for (int j = 0; j < m.getCols(); j++) {
			fout << m.getElement(i, j).toString() << " ";
		}
		fout << "\n";
	}
}

void MatrixUtils::writeDoubleMatrix(std::string path, Matrix<double>& m)
{
	std::ofstream fout(path);
	fout << m.getRows() << " " << m.getCols() << "\n";
	for (int i = 0; i < m.getRows(); i++) {
		for (int j = 0; j < m.getCols(); j++) {
			fout << m.getElement(i, j) << " ";
		}
		fout << "\n";
	}
}

ComplexNumber MatrixUtils::parseComplexNumber(std::string number)
{
	std::vector<std::string> v; //Use vector to add the words

	std::size_t prev_pos = 0, pos;
	while ((pos = number.find_first_of("+-", prev_pos)) != std::string::npos)
	{
		if (pos > prev_pos)
			v.push_back(number.substr(prev_pos, pos - prev_pos));
		prev_pos = pos + 1;
	}
	if (prev_pos< number.length())
		v.push_back(number.substr(prev_pos, std::string::npos));

	if (number.find("+") != std::string::npos) {
		return ComplexNumber(std::stoi(v[0]), std::stoi(v[1]));
	}
	else {
		return ComplexNumber(std::stoi(v[0]), -std::stoi(v[1]));
	}
}
