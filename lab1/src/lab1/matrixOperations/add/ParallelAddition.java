package lab1.matrixOperations.add;

import lab1.Matrix;
import lab1.matrixOperations.IMatrixOperation;

/**
 * Created by Sebi on 06-Oct-17.
 */
public class ParallelAddition implements IMatrixOperation {
    @Override
    public Matrix calculate(Matrix a, Matrix b, Integer nrThreads) {
        System.out.println("Running parallel add, using " + nrThreads + " threads");

        // checks valid rows and columns
        if (a.getCols() != b.getCols() || a.getRows() != b.getRows()){
            return null;
        }

        int rows = a.getRows();
        int cols = a.getCols();
        int len = a.getCols() * a.getRows();
        double[][] res = new double[rows][cols];
        Matrix matRes = new Matrix(rows, cols, res);

        // checks valid nr of threads
        if (nrThreads < 1 || nrThreads > len){
            System.out.println("Invalid nr of threads");
            return null;
        }

        //timestamp
        long before = System.currentTimeMillis();

        // thread operations
        int elemPerTh = len / nrThreads;
        int extraElems = len % nrThreads;
        int start = 0; int end = elemPerTh;

        Thread[] threads = new Thread[nrThreads];
        for (int i = 0; i < nrThreads; i++){
            if (i < extraElems){ // consider the extra elements
                end++;
            }

            threads[i] = new Thread(new ThreadAddMat(a, b, matRes, start, end));
            threads[i].start();

            // update the thread processing interval
            start = end;
            end += elemPerTh;
        }

        // wait for all threads
        for (int i = 0; i < nrThreads; i++){
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long after = System.currentTimeMillis();
        System.out.println("\nParallel add ran for " + (after - before) + " milis\n");


        return matRes;
    }
}
