package matrix.types;

import matrix.MatrixException;

/**
 * Created by Sebi on 24-Oct-17.
 */
public class DoubleElem implements MatrixElement {
    private double value;

    public DoubleElem(double value){
        this.value = value;
    }

    @Override
    public MatrixElement add(MatrixElement other) throws MatrixException {
        if (other instanceof DoubleElem){
            return new DoubleElem(value + ((DoubleElem)other).getValue());
        } else {
            throw new MatrixException("Invalid operation");
        }
    }

    @Override
    public MatrixElement multiply(MatrixElement other) throws MatrixException {
        if (other instanceof DoubleElem) {
            return new DoubleElem(value * ((DoubleElem) other).getValue());
        } else {
            throw new MatrixException("Invalid operation");
        }
    }

    @Override
    public MatrixElement divide(MatrixElement other) throws MatrixException {
        if (other instanceof DoubleElem) {
            return new DoubleElem(value / ((DoubleElem) other).getValue());
        } else {
            throw new MatrixException("Invalid operation");
        }
    }

    public double getValue(){
        return value;
    }

    public void setValue(double value){
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DoubleElem that = (DoubleElem) o;

        return Double.compare(that.value, value) == 0;
    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(value);
        return (int) (temp ^ (temp >>> 32));
    }

    @Override
    public String toString() {
        return String.format("%.2f", value);
    }
}
