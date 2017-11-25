#include "stdafx.h"
#include "CppUnitTest.h"

#include "Matrix.h"
#include "ComplexNumber.h"
#include "MatrixGenerator.h"
#include "ParallelCalculator.h"
#include "MatrixOperations.h"
#include "SerialCalculator.h"
#include "MatrixUtils.h"

#include<iostream>

using namespace Microsoft::VisualStudio::CppUnitTestFramework;


namespace Tests
{

	TEST_CLASS(TestMatrixMultiply)
	{
	public:

		TEST_METHOD(MutiplyTwoIntMatrixes)
		{

			Logger::WriteMessage((std::string("Running 3x3 multiply int test")).c_str());
			MatrixGenerator mg = MatrixGenerator();

			Matrix<int> m1 = mg.getRandomIntMatrix(3, 3);
			Matrix<int> m2 = mg.getRandomIntMatrix(3, 3);


			Matrix<int> m3 = Matrix<int>(3, 3);
			double parT = ParallelCalculator<int>::calculate(m1, m2, m3, 3, MatrixOperations<int>::matrixMultiplication);

			Matrix<int> m4 = Matrix<int>(3, 3);
			double serialT = SerialCalculator<int>::calculate(m1, m2, m4, MatrixOperations<int>::matrixMultiplication);

			Assert::IsTrue(m3 == m4);

			Logger::WriteMessage((std::to_string(parT) + " for 3 threads").c_str());
			Logger::WriteMessage((std::to_string(serialT) + " for serial operations").c_str());
		}

		TEST_METHOD(MultiplyTwoComplexMatrixes)
		{
			Logger::WriteMessage((std::string("Running 3x3 multiply complex test")).c_str());
			MatrixGenerator mg = MatrixGenerator();
			Matrix<ComplexNumber> m1 = mg.getRandomComplexNumberMatrix(3, 3);
			Matrix<ComplexNumber> m2 = mg.getRandomComplexNumberMatrix(3, 3);
			Matrix<ComplexNumber> m3 = Matrix<ComplexNumber>(3, 3);
			double parT = ParallelCalculator<ComplexNumber>::calculate(m1, m2, m3, 5, MatrixOperations<ComplexNumber>::matrixMultiplication);

			Matrix<ComplexNumber> m4 = Matrix<ComplexNumber>(3, 3);
			double serialT = SerialCalculator<ComplexNumber>::calculate(m1, m2, m4, MatrixOperations<ComplexNumber>::matrixMultiplication);

			Assert::IsTrue(m3 == m4);

			Logger::WriteMessage((std::to_string(parT) + " for 5 threads").c_str());
			Logger::WriteMessage((std::to_string(serialT) + " for serial operations").c_str());
		}

		TEST_METHOD(MultiplyTwoInt2)
		{
			Logger::WriteMessage((std::string("Running 4x2 2x3 multiply int test")).c_str());
			MatrixGenerator mg = MatrixGenerator();

			std::vector<std::vector<int>> m1Values = { { 1, 2 },{ 2, 3 },{ 2, 3 }, {-1, -1} };
			std::vector<std::vector<int>> m2Values = { { 1, 2, 3 },{ 1, 2, 3 } };

			Matrix<int> m1 = Matrix<int>(4, 2);
			m1.setData(m1Values);
			Matrix<int> m2 = Matrix<int>(2, 3);
			m2.setData(m2Values);

			std::vector<std::vector<int>> expectedValues = { { 3,6,9 },{ 5,10,15 },{ 5,10,15 }, {-2,-4,-6} };
			Matrix<int> expected = Matrix<int>(4, 3);
			expected.setData(expectedValues);

			Matrix<int> m3 = Matrix<int>(4, 3);
			double parT = ParallelCalculator<int>::calculate(m1, m2, m3, 8, MatrixOperations<int>::matrixMultiplication);

			Matrix<int> m4 = Matrix<int>(4, 3);
			double serialT = SerialCalculator<int>::calculate(m1, m2, m4, MatrixOperations<int>::matrixMultiplication);

			Assert::IsTrue(m3 == m4);
			Assert::IsTrue(m3 == expected);
			Assert::IsTrue(m4 == expected);

			Logger::WriteMessage((std::to_string(parT) + " for 8 threads").c_str());
			Logger::WriteMessage((std::to_string(serialT) + " for serial operations").c_str());
		}

		

		TEST_METHOD(MultiplyTwoComplexMatrixes2) {
			Logger::WriteMessage((std::string("Running 2x2 multiply complex test")).c_str());
			MatrixGenerator mg = MatrixGenerator();

			std::vector<std::vector<ComplexNumber>> m1Values = { { ComplexNumber(1,1), ComplexNumber(1,-1) },{ ComplexNumber(2,2), ComplexNumber(4,4) } };
			std::vector<std::vector<ComplexNumber>> m2Values = { { ComplexNumber(2,2), ComplexNumber(3,3) },{ ComplexNumber(4,-4), ComplexNumber(2,-2) } };
			Matrix<ComplexNumber> m1 = Matrix<ComplexNumber>(2, 2);
			Matrix<ComplexNumber> m2 = Matrix<ComplexNumber>(2, 2);
			Matrix<ComplexNumber> m3 = Matrix<ComplexNumber>(2, 2);
			double parT = ParallelCalculator<ComplexNumber>::calculate(m1, m2, m3, 2, MatrixOperations<ComplexNumber>::matrixMultiplication);

			Matrix<ComplexNumber> m4 = Matrix<ComplexNumber>(2, 2);
			double serialT = SerialCalculator<ComplexNumber>::calculate(m1, m2, m4, MatrixOperations<ComplexNumber>::matrixMultiplication);

			Assert::IsTrue(m3 == m4);

			std::vector<std::vector<ComplexNumber>> expectedValues = { { ComplexNumber(0,-4), ComplexNumber(0,2) },{ ComplexNumber(32,8), ComplexNumber(16,12) } };
			Matrix<ComplexNumber> expected = Matrix<ComplexNumber>(2, 2);
			Assert::IsTrue(m3 == expected);
			Assert::IsTrue(m4 == expected);

			Logger::WriteMessage((std::to_string(parT) + " for 2 threads").c_str());
			Logger::WriteMessage((std::to_string(serialT) + " for serial operations").c_str());
		}

		TEST_METHOD(MultiplyTwoIntMatrixes3)
		{
			Logger::WriteMessage((std::string("Running 1000x1000 multiply int test")).c_str());
			MatrixGenerator mg = MatrixGenerator();
			Matrix<int> m1 = mg.getRandomIntMatrix(1000, 1000);
			Matrix<int> m2 = mg.getRandomIntMatrix(1000, 1000);
			Matrix<int> m3 = Matrix<int>(1000, 1000);
			double parT = ParallelCalculator<int>::calculate(m1, m2, m3, 4, MatrixOperations<int>::matrixMultiplication);

			Matrix<int> m4 = Matrix<int>(1000, 1000);
			double serialT = SerialCalculator<int>::calculate(m1, m2, m4, MatrixOperations<int>::matrixMultiplication);

			Assert::IsTrue(m3 == m4);
			Logger::WriteMessage((std::to_string(parT) + " for 4 threads").c_str());
			Logger::WriteMessage((std::to_string(serialT) + " for serial operations").c_str());
		}

		TEST_METHOD(MultiplyTwoIntMatrixes4)
		{
			Logger::WriteMessage((std::string("Running 1000x1000 multiply int test")).c_str());
			MatrixGenerator mg = MatrixGenerator();
			Matrix<int> m1 = mg.getRandomIntMatrix(2000, 1000);
			Matrix<int> m2 = mg.getRandomIntMatrix(1000, 2000);
			Matrix<int> m3 = Matrix<int>(2000, 2000);
			double parT = ParallelCalculator<int>::calculate(m1, m2, m3, 6, MatrixOperations<int>::matrixMultiplication);

			Matrix<int> m4 = Matrix<int>(2000, 2000);
			double serialT = SerialCalculator<int>::calculate(m1, m2, m4, MatrixOperations<int>::matrixMultiplication);

			Assert::IsTrue(m3 == m4);

			Logger::WriteMessage((std::to_string(parT) + " for 6 threads").c_str());
			Logger::WriteMessage((std::to_string(serialT) + " for serial operations").c_str());
		}

		TEST_METHOD(MultiplyTwoComplexMatrixes3)
		{
			Logger::WriteMessage((std::string("Running 1000x1000 multiply complex test")).c_str());
			MatrixGenerator mg = MatrixGenerator();
			Matrix<ComplexNumber> m1 = mg.getRandomComplexNumberMatrix(1000, 1000);
			Matrix<ComplexNumber> m2 = mg.getRandomComplexNumberMatrix(1000, 1000);
			Matrix<ComplexNumber> m3 = Matrix<ComplexNumber>(1000, 1000);
			double parT = ParallelCalculator<ComplexNumber>::calculate(m1, m2, m3, 10, MatrixOperations<ComplexNumber>::matrixMultiplication);

			Matrix<ComplexNumber> m4 = Matrix<ComplexNumber>(1000, 1000);
			double serialT = SerialCalculator<ComplexNumber>::calculate(m1, m2, m4, MatrixOperations<ComplexNumber>::matrixMultiplication);

			Assert::IsTrue(m3 == m4);

			Logger::WriteMessage((std::to_string(parT) + " for 10 threads").c_str());
			Logger::WriteMessage((std::to_string(serialT) + " for serial operations").c_str());
		}

		void runIntMatrixMultiplicationSimulation(int nrTh, int r1, int c1, int r2, int c2) {
			Logger::WriteMessage(std::string("int data for " + std::to_string(nrTh) + " threads").c_str());
			Logger::WriteMessage((std::string("Running" + std::to_string(r1) + "x" + std::to_string(c1) + " " + std::to_string(r2) + "x" + std::to_string(c2) +" multiply test")).c_str());
			MatrixGenerator mg = MatrixGenerator();

			Matrix<int> m1 = mg.getRandomIntMatrix(r1, c1);
			Matrix<int> m2 = mg.getRandomIntMatrix(r2, c2);


			Matrix<int> m3 = Matrix<int>(r1, c2);
			double parT = ParallelCalculator<int>::calculate(m1, m2, m3, nrTh, MatrixOperations<int>::matrixMultiplication);

			Matrix<int> m4 = Matrix<int>(r1, c2);
			double serialT = SerialCalculator<int>::calculate(m1, m2, m4, MatrixOperations<int>::matrixMultiplication);

			Assert::IsTrue(m3 == m4);

			Logger::WriteMessage((std::to_string(parT) + " for " + std::to_string(nrTh) + " threads").c_str());
			Logger::WriteMessage((std::to_string(serialT) + " for serial operations").c_str());
			Logger::WriteMessage(std::string("---------------------------------------------").c_str());
		}

		TEST_METHOD(intSimulation)
		{
			for (int i = 2; i <= 8; i += 2) {
				runIntMatrixMultiplicationSimulation(i, 1000, 1000, 1000, 1000);
			}
		}

		void runComplexMatrixMultiplicationSimulation(int nrTh, int r1, int c1, int r2, int c2) {
			Logger::WriteMessage(std::string("complex number data for " + std::to_string(nrTh) + " threads").c_str());
			Logger::WriteMessage((std::string("Running" + std::to_string(r1) + "x" + std::to_string(c1) + " " + std::to_string(r2) + "x" + std::to_string(c2) + " multiply test")).c_str());
			MatrixGenerator mg = MatrixGenerator();

			Matrix<ComplexNumber> m1 = mg.getRandomComplexNumberMatrix(r1, c1);
			Matrix<ComplexNumber> m2 = mg.getRandomComplexNumberMatrix(r2, c2);


			Matrix<ComplexNumber> m3 = Matrix<ComplexNumber>(r1, c2);
			double parT = ParallelCalculator<ComplexNumber>::calculate(m1, m2, m3, nrTh, MatrixOperations<ComplexNumber>::matrixMultiplication);

			Matrix<ComplexNumber> m4 = Matrix<ComplexNumber>(r1, c2);
			double serialT = SerialCalculator<ComplexNumber>::calculate(m1, m2, m4, MatrixOperations<ComplexNumber>::matrixMultiplication);

			Assert::IsTrue(m3 == m4);

			Logger::WriteMessage((std::to_string(parT) + " for " + std::to_string(nrTh) + " threads").c_str());
			Logger::WriteMessage((std::to_string(serialT) + " for serial operations").c_str());
			Logger::WriteMessage(std::string("------------------------------------------------").c_str());
		}

		TEST_METHOD(complexSimulation)
		{
			for (int i = 2; i <= 8; i += 2) {
				runComplexMatrixMultiplicationSimulation(i, 1000, 1000, 1000, 1000);
			}
		}

		void runDoubleMatrixMultiplicationSimulation(int nrTh, int r1, int c1, int r2, int c2) {
			Logger::WriteMessage(std::string("double data for " + std::to_string(nrTh) + " threads").c_str());
			Logger::WriteMessage((std::string("Running" + std::to_string(r1) + "x" + std::to_string(c1) + " " + std::to_string(r2) + "x" + std::to_string(c2) + " multiply test")).c_str());
			MatrixGenerator mg = MatrixGenerator();

			Matrix<double> m1 = mg.getRandomDoubleMatrix(r1, c1);
			Matrix<double> m2 = mg.getRandomDoubleMatrix(r2, c2);


			Matrix<double> m3 = Matrix<double>(r1, c2);
			double parT = ParallelCalculator<double>::calculate(m1, m2, m3, nrTh, MatrixOperations<double>::matrixMultiplication);

			Matrix<double> m4 = Matrix<double>(r1, c2);
			double serialT = SerialCalculator<double>::calculate(m1, m2, m4, MatrixOperations<double>::matrixMultiplication);

			Assert::IsTrue(m3 == m4);

			Logger::WriteMessage((std::to_string(parT) + " for " + std::to_string(nrTh) + " threads").c_str());
			Logger::WriteMessage((std::to_string(serialT) + " for serial operations").c_str());
			Logger::WriteMessage(std::string("---------------------------------------------").c_str());
		}

		TEST_METHOD(doubleSimulation)
		{
			for (int i = 2; i <= 8; i += 2) {
				runDoubleMatrixMultiplicationSimulation(i, 1000, 1000, 1000, 1000);
			}
		}

	};
}