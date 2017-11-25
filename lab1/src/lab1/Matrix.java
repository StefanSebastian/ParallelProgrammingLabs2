package lab1;

/**
 * Created by Sebi on 06-Oct-17.
 */
/*
Wrapper class for a matrix
 */
public class Matrix {
    private int rows;
    private int cols;
    private double[][] data;

    public Matrix(int rows, int cols, double[][] data){
        this.rows = rows;
        this.cols = cols;
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

    public double[][] getData() {
        return data;
    }

    public void setData(double[][] data) {
        this.data = data;
    }

    @Override
    public String toString(){
        String mat = rows + " " + cols + "\n";

        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                mat = mat + data[i][j] + " ";
            }
            mat += "\n";
        }

        return mat;
    }

    @Override
    public boolean equals(Object other){
        if (other == null){
            return false;
        }
        if (!(other instanceof Matrix)){
            return false;
        }
        Matrix otMat = (Matrix)other;

        if (cols != otMat.getCols()){
            return false;
        }

        if (rows != otMat.getRows()){
            return false;
        }

        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                if (data[i][j] != otMat.getData()[i][j]){
                    return false;
                }
            }
        }

        return true;
    }
}
