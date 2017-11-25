package lab1.matrixOperations.add.experimental;

import lab1.Matrix;
import lab1.matrixOperations.IMatrixOperation;

/**
 * Created by Sebi on 06-Oct-17.
 */
public class ParallelAdditionTransformArray implements IMatrixOperation {
    @Override
    public Matrix calculate(Matrix a, Matrix b, Integer nrThreads) {
        System.out.println("Running parallel add with array transform, using " + nrThreads + " threads");

        if (a.getCols() != b.getCols() || a.getRows() != b.getRows()){
            return null;
        }

        int rows = a.getRows();
        int cols = a.getCols();
        int len = rows * cols;

        if (nrThreads < 1 || nrThreads > len){
            System.out.println("Invalid nr of threads");
            return null;
        }

        // array transformation
        double[] aArray = new double[len];
        double[] bArray = new double[len];
        double[] resArray = new double[len];

        int idx = 0;
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                aArray[idx] = a.getData()[i][j];
                bArray[idx] = b.getData()[i][j];
                idx++;
            }
        }

        // thread operations
        int elemPerTh = len / nrThreads;
        int extraElems = len % nrThreads;
        int start = 0; int end = elemPerTh;

        Thread[] threads = new Thread[nrThreads];
        for (int i = 0; i < nrThreads; i++){
            if (i < extraElems){
                end++;
            }

            threads[i] = new Thread(new ThreadAddArray(aArray, bArray, resArray, start, end));
            threads[i].start();

            start = end;
            end += elemPerTh;
        }

        // waits for all threads
        for (int i = 0; i < nrThreads; i++){
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // reconstruct matrix
        double[][] matRes = new double[rows][cols];
        idx = 0;
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
               matRes[i][j] = resArray[idx]; idx++;
            }
        }

        return new Matrix(rows, cols, matRes);
    }
}
