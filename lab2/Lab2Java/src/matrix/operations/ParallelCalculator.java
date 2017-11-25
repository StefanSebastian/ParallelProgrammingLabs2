package matrix.operations;

import matrix.Matrix;
import matrix.MatrixException;
import matrix.operations.operators.MatrixElementOperator;

/**
 * Created by Sebi on 21-Oct-17.
 */
public class ParallelCalculator {

    public void calculate(Matrix m1, Matrix m2, Matrix res, int nrThreads, MatrixElementOperator operator) throws MatrixException{
        int rows = res.getRows();
        int cols = res.getCols();
        int len = rows * cols;

        if (nrThreads < 1 || nrThreads > len){
            throw new MatrixException("Invalid number of threads");
        }

        // timestamp
        long startTime = System.currentTimeMillis();

        // thread operations
        int elemPerThread = len / nrThreads;
        int extraElems = len % nrThreads;
        int start = 0; int end = elemPerThread;

        Thread[] threads = new Thread[nrThreads];
        for (int i = 0; i < nrThreads; i++) {
            if (i < extraElems) {
                end++; // consider the extra elements
            }

            threads[i] = new Thread(new ParallelWorker(m1, m2, res, start, end, operator));
            threads[i].start();

            start = end;
            end += elemPerThread;
        }

        for (int i = 0; i < nrThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Parallel operation with " + nrThreads + " threads ran for " + (endTime - startTime));
    }
}
