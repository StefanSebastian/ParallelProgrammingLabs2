package matrix;

import matrix.types.MatrixElement;

import java.util.Arrays;

/**
 * Created by Sebi on 21-Oct-17.
 */
public class Matrix {
    private MatrixElement[][] data;
    private int rows;
    private int cols;

    public Matrix(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        data = new MatrixElement[rows][cols];
    }

    public void setElement(int i, int j, MatrixElement elem){
        data[i][j] = elem;
    }

    public MatrixElement getElement(int i, int j){
        return data[i][j];
    }

    public MatrixElement[][] getData() {
        return data;
    }

    public void setData(MatrixElement[][] data) {
        this.data = data;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Matrix matrix = (Matrix) o;

        if (rows != matrix.rows) return false;
        if (cols != matrix.cols) return false;
        return Arrays.deepEquals(data, matrix.data);
    }

    @Override
    public int hashCode() {
        int result = Arrays.deepHashCode(data);
        result = 31 * result + rows;
        result = 31 * result + cols;
        return result;
    }
}
