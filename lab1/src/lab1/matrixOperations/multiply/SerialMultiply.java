package lab1.matrixOperations.multiply;

import lab1.Matrix;
import lab1.matrixOperations.IMatrixOperation;

/**
 * Created by Sebi on 07-Oct-17.
 */
public class SerialMultiply implements IMatrixOperation {

    /*
    dot product between row identified with idx1 in matrix a
     and column identified with idx2 in matrix b
     */
    private double dotProduct(Matrix a, Matrix b, int idx1, int idx2){
        double prod = 0;
        for (int i = 0; i < a.getCols(); i++){
            prod += a.getData()[idx1][i] * b.getData()[i][idx2];
        }
        return prod;
    }

    @Override
    public Matrix calculate(Matrix a, Matrix b, Integer nrThreads) {
        System.out.println("Running serial multiply");

        if (a.getCols() != b.getRows()){
            System.out.println("Invalid matrixes");
            return null;
        }

        // time stamp
        long before = System.currentTimeMillis();

        int rows = a.getRows();
        int cols = b.getCols();
        double[][] res = new double[rows][cols];

        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                res[i][j] = dotProduct(a, b, i, j);
            }
        }

        long after = System.currentTimeMillis();
        System.out.println("\nSerial multiply ran for " + (after - before) + " milis\n");

        return new Matrix(rows, cols, res);
    }
}
