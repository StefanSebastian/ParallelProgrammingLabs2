#include "ComplexNumber.h"
#include<iostream>
using namespace std;

int main() {
	ComplexNumber a;
	a.setA(3);
	a.setB(2);
	ComplexNumber b;
	b.setA(1);
	b.setB(-2);
	ComplexNumber c = a * b;
	cout << c.toString();

	while (1);
}