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

	TEST_CLASS(TestPointOperator)
	{
	public:

		TEST_METHOD(AddTwoIntMatrixes)
		{

			Logger::WriteMessage((std::string("Running 3x3 pointOperator test")).c_str());
			MatrixGenerator mg = MatrixGenerator();

			Matrix<int> m1 = mg.getRandomIntMatrix(3, 3);
			Matrix<int> m2 = mg.getRandomIntMatrix(3, 3);


			Matrix<int> m3 = Matrix<int>(3, 3);
			double parT = ParallelCalculator<int>::calculate(m1, m2, m3, 3, MatrixOperations<int>::pointIntOperator);

			Matrix<int> m4 = Matrix<int>(3, 3);
			double serialT = SerialCalculator<int>::calculate(m1, m2, m4, MatrixOperations<int>::pointIntOperator);

			Assert::IsTrue(m3 == m4);

			Logger::WriteMessage((std::to_string(parT) + " for 3 threads").c_str());
			Logger::WriteMessage((std::to_string(serialT) + " for serial operations").c_str());
		}

		TEST_METHOD(AddTwoInt2)
		{
			Logger::WriteMessage((std::string("Running 3x3 pointOperator int test")).c_str());
			MatrixGenerator mg = MatrixGenerator();

			std::vector<std::vector<int>> m1Values = { { 1, 2 },{ 1, 2 } };
			std::vector<std::vector<int>> m2Values = { { 2, 2 },{ 2, 3 } };

			Matrix<int> m1 = Matrix<int>(2, 2);
			m1.setData(m1Values);
			Matrix<int> m2 = Matrix<int>(2, 2);
			m2.setData(m2Values);

			std::vector<std::vector<int>> expectedValues = { { 0, 1 },{ 0, 1 } };
			Matrix<int> expected = Matrix<int>(2, 2);
			expected.setData(expectedValues);

			Matrix<int> m3 = Matrix<int>(2, 2);
			double parT = ParallelCalculator<int>::calculate(m1, m2, m3, 3, MatrixOperations<int>::pointIntOperator);

			Matrix<int> m4 = Matrix<int>(2, 2);
			double serialT = SerialCalculator<int>::calculate(m1, m2, m4, MatrixOperations<int>::pointIntOperator);

			Assert::IsTrue(m3 == m4);
			Assert::IsTrue(m3 == expected);
			Assert::IsTrue(m4 == expected);

			Logger::WriteMessage((std::to_string(parT) + " for 3 threads").c_str());
			Logger::WriteMessage((std::to_string(serialT) + " for serial operations").c_str());
		}

		TEST_METHOD(AddTwoComplex)
		{
			Logger::WriteMessage((std::string("Running 3x3 pointOp complex test")).c_str());
			MatrixGenerator mg = MatrixGenerator();
			Matrix<ComplexNumber> m1 = mg.getRandomComplexNumberMatrix(3, 3);
			Matrix<ComplexNumber> m2 = mg.getRandomComplexNumberMatrix(3, 3);
			Matrix<ComplexNumber> m3 = Matrix<ComplexNumber>(3, 3);
			double parT = ParallelCalculator<ComplexNumber>::calculate(m1, m2, m3, 4, MatrixOperations<ComplexNumber>::pointComplexNrOperator);

			Matrix<ComplexNumber> m4 = Matrix<ComplexNumber>(3, 3);
			double serialT = SerialCalculator<ComplexNumber>::calculate(m1, m2, m4, MatrixOperations<ComplexNumber>::pointComplexNrOperator);

			Assert::IsTrue(m3 == m4);

			Logger::WriteMessage((std::to_string(parT) + " for 8 threads").c_str());
			Logger::WriteMessage((std::to_string(serialT) + " for serial operations").c_str());
		}

		void runIntSimulation(int nrTh, int r, int c) {
			Logger::WriteMessage(std::string("int data for " + std::to_string(nrTh) + " threads").c_str());
			Logger::WriteMessage((std::string("Running" + std::to_string(r) + "x" + std::to_string(c) + " pointOperator test")).c_str());
			MatrixGenerator mg = MatrixGenerator();

			Matrix<int> m1 = mg.getRandomIntMatrix(r, c);
			Matrix<int> m2 = mg.getRandomIntMatrix(r, c);


			Matrix<int> m3 = Matrix<int>(r, c);
			double parT = ParallelCalculator<int>::calculate(m1, m2, m3, nrTh, MatrixOperations<int>::pointIntOperator);

			Matrix<int> m4 = Matrix<int>(r, c);
			double serialT = SerialCalculator<int>::calculate(m1, m2, m4, MatrixOperations<int>::pointIntOperator);

			Assert::IsTrue(m3 == m4);

			Logger::WriteMessage((std::to_string(parT) + " for " + std::to_string(nrTh) + " threads").c_str());
			Logger::WriteMessage((std::to_string(serialT) + " for serial operations").c_str());
			Logger::WriteMessage(std::string("---------------------------------------------").c_str());
		}

		TEST_METHOD(intSimulation)
		{
			for (int i = 2; i <= 8; i += 2) {
				runIntSimulation(i, 1000, 1000);
			}
		}

		void runComplexSimulation(int nrTh, int r, int c) {
			Logger::WriteMessage(std::string("complex number data for " + std::to_string(nrTh) + " threads").c_str());
			Logger::WriteMessage((std::string("Running" + std::to_string(r) + "x" + std::to_string(c) + " pointOperator test")).c_str());
			MatrixGenerator mg = MatrixGenerator();

			Matrix<ComplexNumber> m1 = mg.getRandomComplexNumberMatrix(r, c);
			Matrix<ComplexNumber> m2 = mg.getRandomComplexNumberMatrix(r, c);


			Matrix<ComplexNumber> m3 = Matrix<ComplexNumber>(r, c);
			double parT = ParallelCalculator<ComplexNumber>::calculate(m1, m2, m3, nrTh, MatrixOperations<ComplexNumber>::pointOperator);

			Matrix<ComplexNumber> m4 = Matrix<ComplexNumber>(r, c);
			double serialT = SerialCalculator<ComplexNumber>::calculate(m1, m2, m4, MatrixOperations<ComplexNumber>::pointOperator);

			Assert::IsTrue(m3 == m4);

			Logger::WriteMessage((std::to_string(parT) + " for " + std::to_string(nrTh) + " threads").c_str());
			Logger::WriteMessage((std::to_string(serialT) + " for serial operations").c_str());
			Logger::WriteMessage(std::string("------------------------------------------------").c_str());
		}

		TEST_METHOD(complexSimulation)
		{
			for (int i = 2; i <= 8; i += 2) {
				runComplexSimulation(i, 1000, 1000);
			}
		}


		void runDoubleSimulation(int nrTh, int r, int c) {
			Logger::WriteMessage(std::string("double data for " + std::to_string(nrTh) + " threads").c_str());
			Logger::WriteMessage((std::string("Running" + std::to_string(r) + "x" + std::to_string(c) + " pointOperator test")).c_str());
			MatrixGenerator mg = MatrixGenerator();

			Matrix<double> m1 = mg.getRandomDoubleMatrix(r, c);
			Matrix<double> m2 = mg.getRandomDoubleMatrix(r, c);


			Matrix<double> m3 = Matrix<double>(r, c);
			double parT = ParallelCalculator<double>::calculate(m1, m2, m3, nrTh, MatrixOperations<double>::pointOperator);

			Matrix<double> m4 = Matrix<double>(r, c);
			double serialT = SerialCalculator<double>::calculate(m1, m2, m4, MatrixOperations<double>::pointOperator);

			Assert::IsTrue(m3 == m4);

			Logger::WriteMessage((std::to_string(parT) + " for " + std::to_string(nrTh) + " threads").c_str());
			Logger::WriteMessage((std::to_string(serialT) + " for serial operations").c_str());
			Logger::WriteMessage(std::string("---------------------------------------------").c_str());
		}

		TEST_METHOD(doubleSimulation)
		{
			for (int i = 2; i <= 8; i += 2) {
				runDoubleSimulation(i, 1000, 1000);
			}
		}
		
	};
}