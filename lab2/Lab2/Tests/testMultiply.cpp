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

	TEST_CLASS(TestMultiply)
	{
	public:

		void runIntMultiplySimulation(int nrTh, int r, int c) {
			Logger::WriteMessage(std::string("int data for " + std::to_string(nrTh) + " threads").c_str());
			Logger::WriteMessage((std::string("Running" + std::to_string(r) + "x" + std::to_string(c) + " " + std::to_string(r) + "x" + std::to_string(c) + " multiply test")).c_str());
			MatrixGenerator mg = MatrixGenerator();

			Matrix<int> m1 = mg.getRandomIntMatrix(r, c);
			Matrix<int> m2 = mg.getRandomIntMatrix(r, c);


			Matrix<int> m3 = Matrix<int>(r, c);
			double parT = ParallelCalculator<int>::calculate(m1, m2, m3, nrTh, MatrixOperations<int>::multiply);

			Matrix<int> m4 = Matrix<int>(r, c);
			double serialT = SerialCalculator<int>::calculate(m1, m2, m4, MatrixOperations<int>::multiply);

			Assert::IsTrue(m3 == m4);

			Logger::WriteMessage((std::to_string(parT) + " for " + std::to_string(nrTh) + " threads").c_str());
			Logger::WriteMessage((std::to_string(serialT) + " for serial operations").c_str());
			Logger::WriteMessage(std::string("---------------------------------------------").c_str());
		}

		TEST_METHOD(intSimulation)
		{
			for (int i = 2; i <= 8; i += 2) {
				runIntMultiplySimulation(i, 1000, 1000);
			}
		}

		void runComplexMultiplySimulation(int nrTh, int r, int c) {
			Logger::WriteMessage(std::string("complex number data for " + std::to_string(nrTh) + " threads").c_str());
			Logger::WriteMessage((std::string("Running" + std::to_string(r) + "x" + std::to_string(c) + " " + std::to_string(r) + "x" + std::to_string(c) + " multiply test")).c_str());
			MatrixGenerator mg = MatrixGenerator();

			Matrix<ComplexNumber> m1 = mg.getRandomComplexNumberMatrix(r, c);
			Matrix<ComplexNumber> m2 = mg.getRandomComplexNumberMatrix(r, c);


			Matrix<ComplexNumber> m3 = Matrix<ComplexNumber>(r, c);
			double parT = ParallelCalculator<ComplexNumber>::calculate(m1, m2, m3, nrTh, MatrixOperations<ComplexNumber>::multiply);

			Matrix<ComplexNumber> m4 = Matrix<ComplexNumber>(r, c);
			double serialT = SerialCalculator<ComplexNumber>::calculate(m1, m2, m4, MatrixOperations<ComplexNumber>::multiply);

			Assert::IsTrue(m3 == m4);

			Logger::WriteMessage((std::to_string(parT) + " for " + std::to_string(nrTh) + " threads").c_str());
			Logger::WriteMessage((std::to_string(serialT) + " for serial operations").c_str());
			Logger::WriteMessage(std::string("------------------------------------------------").c_str());
		}

		TEST_METHOD(complexSimulation)
		{
			for (int i = 2; i <= 8; i += 2) {
				runComplexMultiplySimulation(i, 1000, 1000);
			}
		}

		void runDoubleMultiplySimulation(int nrTh, int r, int c) {
			Logger::WriteMessage(std::string("double data for " + std::to_string(nrTh) + " threads").c_str());
			Logger::WriteMessage((std::string("Running" + std::to_string(r) + "x" + std::to_string(c) + " " + std::to_string(r) + "x" + std::to_string(c) + " multiply test")).c_str());
			MatrixGenerator mg = MatrixGenerator();

			Matrix<double> m1 = mg.getRandomDoubleMatrix(r, c);
			Matrix<double> m2 = mg.getRandomDoubleMatrix(r, c);


			Matrix<double> m3 = Matrix<double>(r, c);
			double parT = ParallelCalculator<double>::calculate(m1, m2, m3, nrTh, MatrixOperations<double>::multiply);

			Matrix<double> m4 = Matrix<double>(r, c);
			double serialT = SerialCalculator<double>::calculate(m1, m2, m4, MatrixOperations<double>::multiply);

			Assert::IsTrue(m3 == m4);

			Logger::WriteMessage((std::to_string(parT) + " for " + std::to_string(nrTh) + " threads").c_str());
			Logger::WriteMessage((std::to_string(serialT) + " for serial operations").c_str());
			Logger::WriteMessage(std::string("---------------------------------------------").c_str());
		}

		TEST_METHOD(doubleSimulation)
		{
			for (int i = 2; i <= 8; i += 2) {
				runDoubleMultiplySimulation(i, 1000, 1000);
			}
		}

	};
}