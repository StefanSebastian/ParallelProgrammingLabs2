package matrix.operations;

import matrix.Matrix;
import matrix.MatrixException;
import matrix.operations.operators.MatrixElementOperator;

/**
 * Created by Sebi on 21-Oct-17.
 */
public class ParallelWorker implements Runnable {
    private Matrix m1;
    private Matrix m2;
    private Matrix res;
    private int start;
    private int end;
    MatrixElementOperator operator;

    public ParallelWorker(Matrix m1, Matrix m2, Matrix res, int start, int end, MatrixElementOperator operator) {
        this.m1 = m1;
        this.m2 = m2;
        this.res = res;
        this.start = start;
        this.end = end;
        this.operator = operator;
    }

    @Override
    public void run() {
        int cols = res.getCols();
        int startRow = start / cols;
        int startCol = start % cols;
        int endRow = end / cols;
        int endCol = end % cols;

        int i = startRow; int j = startCol;
        while (!(i == endRow && j == endCol)) {
            try {
                res.setElement(i, j, operator.execute(m1, m2, i, j));
            } catch (MatrixException e) {
                e.printStackTrace();
            }

            if (j < cols - 1) {
                j++;
            }
            else {
                j = 0;
                i++;
            }
        }
    }
}
