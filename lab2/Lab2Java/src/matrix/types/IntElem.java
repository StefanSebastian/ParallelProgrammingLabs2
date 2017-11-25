package matrix.types;

import matrix.MatrixException;

/**
 * Created by Sebi on 21-Oct-17.
 */
public class IntElem implements MatrixElement {
    private int value;

    public IntElem(int value) {
        this.value = value;
    }

    @Override
    public MatrixElement add(MatrixElement other) throws MatrixException {
        if (other instanceof IntElem) {
            return new IntElem(value + ((IntElem) other).getValue());
        } else {
            throw new MatrixException("Invalid operation");
        }
    }

    @Override
    public MatrixElement multiply(MatrixElement other) throws MatrixException {
        if (other instanceof IntElem) {
            return new IntElem(value * ((IntElem) other).getValue());
        } else {
            throw new MatrixException("Invalid operation");
        }
    }

    @Override
    public MatrixElement divide(MatrixElement other) throws MatrixException {
        if (other instanceof IntElem) {
            return new IntElem(value / ((IntElem) other).getValue());
        } else {
            throw new MatrixException("Invalid operation");
        }
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IntElem intElem = (IntElem) o;

        return value == intElem.value;
    }

    @Override
    public int hashCode() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
