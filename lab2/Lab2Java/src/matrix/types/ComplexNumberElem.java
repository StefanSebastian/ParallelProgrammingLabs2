package matrix.types;

import matrix.MatrixException;

/**
 * Created by Sebi on 21-Oct-17.
 */
public class ComplexNumberElem implements MatrixElement {
    private double a;
    private double b;

    public ComplexNumberElem(double a, double b) {
        this.a = a;
        this.b = b;
    }


    @Override
    public MatrixElement add(MatrixElement other) throws MatrixException {
        if (other instanceof ComplexNumberElem) {
            ComplexNumberElem ot = (ComplexNumberElem)other;
            return new ComplexNumberElem(a + ot.a, b + ot.b);
        } else {
            throw new MatrixException("Invalid operation");
        }
    }

    @Override
    public MatrixElement multiply(MatrixElement other) throws MatrixException {
        if (other instanceof ComplexNumberElem) {
            ComplexNumberElem ot = (ComplexNumberElem)other;
            return new ComplexNumberElem(a * ot.a - b * ot.b, a * ot.b + b * ot.a);
        } else {
            throw new MatrixException("Invalid operation");
        }
    }

    @Override
    public MatrixElement divide(MatrixElement other) throws MatrixException {
        if (other instanceof ComplexNumberElem) {
            ComplexNumberElem nr = (ComplexNumberElem)other;
            return new ComplexNumberElem((a * nr.a + b * nr.b) / (nr.a * nr.a + nr.b * nr.b),
                    (b * nr.a - a * nr.b) / (nr.a * nr.a + nr.b * nr.b));
        } else {
            throw new MatrixException("Invalid operation");
        }
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComplexNumberElem that = (ComplexNumberElem) o;

        if (Double.compare(that.a, a) != 0) return false;
        return Double.compare(that.b, b) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(a);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(b);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        if (b >= 0){
            return String.format("%.2f", a) + "+" + String.format("%.2f", b) + "i";
        }
        return String.format("%.2f", a) + String.format("%.2f", b) + "i";
    }
}
