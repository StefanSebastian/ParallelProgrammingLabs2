#include "stdafx.h"
#include "CppUnitTest.h"

#include "Matrix.h"
#include "ComplexNumber.h"
#include "MatrixGenerator.h"

#include<iostream>

using namespace Microsoft::VisualStudio::CppUnitTestFramework;


namespace Tests
{		


	TEST_CLASS(UnitTest1)
	{
	public:
		
		TEST_METHOD(Add1)
		{
			ComplexNumber nr;
			nr.setA(3); nr.setB(4);
			ComplexNumber nr2;
			nr2.setA(2); nr2.setB(3);
			ComplexNumber nr3 = nr + nr2;
			Assert::AreEqual(nr.getA(), 3.0);
		}

		TEST_METHOD(Matrix1)
		{
			Matrix<ComplexNumber> matrix = Matrix<ComplexNumber>(3, 3);
			ComplexNumber nr = ComplexNumber(2, 3);
			matrix.setElement(1, 1, nr);
			ComplexNumber nrMat = matrix.getElement(1, 1);
			Assert::IsTrue(nr == nrMat);
		}

		TEST_METHOD(RandomGenerator)
		{
			MatrixGenerator mg = MatrixGenerator();
			Matrix<ComplexNumber> matrix = mg.getRandomComplexNumberMatrix(4, 4);
			Assert::IsTrue(matrix.getCols() == 4);
			Assert::IsTrue(matrix.getRows() == 4);

			for (int i = 0; i < matrix.getRows(); i++) {
				for (int j = 0; j < matrix.getCols(); j++) {
					std::cout << matrix.getElement(i, j).toString() << " ";
				}
			}
		}

		TEST_METHOD(ComplexDivide)
		{
			ComplexNumber n1 = ComplexNumber(20, -4);
			ComplexNumber n2 = ComplexNumber(3, 2);
			ComplexNumber res = ComplexNumber(4, -4);
			ComplexNumber actual = n1 / n2;
			Assert::IsTrue(res == actual);
				
		}


	};
}