package lab1.matrixOperations.add;

import lab1.Matrix;
import lab1.matrixOperations.IMatrixOperation;

/**
 * Created by Sebi on 06-Oct-17.
 */
public class SerialAddition implements IMatrixOperation {
    public Matrix calculate(Matrix a, Matrix b, Integer nrThreads){
        System.out.println("Executing serial addition");

        if ((a.getCols() != b.getCols()) || (a.getRows() != b.getRows())){
            System.out.println("Invalid matrix");
            return null;
        }

        // save time
        long before = System.currentTimeMillis();

        int rows = a.getRows();
        int cols = a.getCols();
        double[][] res = new double[rows][cols];
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
               // System.out.println(i + " " + j);
                res[i][j] = a.getData()[i][j] + b.getData()[i][j];
            }
        }

        long after = System.currentTimeMillis();
        System.out.println("\nSerial add ran for " + (after - before) + " milis\n");

        System.out.println("Finished serial add");
        return new Matrix(rows, cols, res);
    }
}
