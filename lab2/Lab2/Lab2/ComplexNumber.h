#pragma once

#include<string>

/*
Complex number class
exported as dll
*/
class __declspec(dllexport) ComplexNumber {
private:
	double a;
	double b;
public:
	ComplexNumber();
	ComplexNumber(double a, double b);
	ComplexNumber(double a);
	ComplexNumber(const ComplexNumber& nr);
	~ComplexNumber() = default;

	void setA(double a);
	double getA() const;
	void setB(double b);
	double getB() const;

	ComplexNumber operator+(const ComplexNumber& nr);
	ComplexNumber operator*(const ComplexNumber& nr);
	ComplexNumber operator/(const ComplexNumber& nr);
	void operator=(const ComplexNumber& nr);
	bool operator==(const ComplexNumber& nr) const;
	bool operator!=(const ComplexNumber& nr) const;

	std::string toString();
};