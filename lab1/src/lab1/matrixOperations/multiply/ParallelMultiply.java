package lab1.matrixOperations.multiply;

import lab1.Matrix;
import lab1.matrixOperations.IMatrixOperation;

/**
 * Created by Sebi on 07-Oct-17.
 */
public class ParallelMultiply implements IMatrixOperation {
    @Override
    public Matrix calculate(Matrix a, Matrix b, Integer nrThreads) {
        System.out.println("Running parallel multiply with " + nrThreads + " threads");

        // checks valid matrixes
        if (a.getCols() != b.getRows()){
            System.out.println("Invalid matrixes");
            return null;
        }

        int rows = a.getRows();
        int cols = b.getCols();
        int len = rows * cols;
        double[][] res = new double[rows][cols];
        Matrix resMat = new Matrix(rows, cols, res);

        // checks valid nr of threads
        if (nrThreads < 1 || nrThreads > len){
            System.out.println("Invalid nr of threads");
            return null;
        }

        // timestamp
        long before = System.currentTimeMillis();

        // thread operations
        int elemPerTh = len / nrThreads;
        int extraElems = len % nrThreads;
        int start = 0; int end = elemPerTh;

        Thread[] threads = new Thread[nrThreads];
        for (int i = 0; i < nrThreads; i++){
            if (i < extraElems){
                end++;
            }

            threads[i] = new Thread(new ThreadMultiplyMat(a, b, resMat, start, end));
            threads[i].start();

            start = end;
            end += elemPerTh;
        }

        // wait all threads
        for (int i = 0; i < nrThreads; i++){
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long after = System.currentTimeMillis();
        System.out.println("\nParallel multiply ran for " + (after - before) + " milis\n");

        return resMat;
    }
}
