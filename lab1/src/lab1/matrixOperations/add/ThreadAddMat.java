package lab1.matrixOperations.add;

import lab1.Matrix;

/**
 * Created by Sebi on 06-Oct-17.
 */
public class ThreadAddMat implements Runnable {
    
    private Matrix a;
    private Matrix b;
    private Matrix res;
    private int start;
    private int end;

    public ThreadAddMat(Matrix a, Matrix b, Matrix res, int start, int end) {
        this.a = a;
        this.b = b;
        this.res = res;
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        // get the points in matrix between which to calculate
        int cols = a.getCols();
        int rows = a.getRows();

        int startRow = start / cols;
        int startCol = start % cols;

        int endRow = end / cols;
        int endCol = end % cols;

       // System.out.println("Start row: " + startRow + " start col : " + startCol + " end row " + endRow + " endcol " + endCol);

        int i = startRow; int j = startCol;
        while (!(i == endRow && j == endCol)){

            res.getData()[i][j] = a.getData()[i][j] + b.getData()[i][j];

            if (j < cols - 1){
                j++;
            } else {
                j = 0;
                i++;
            }
        }

    }
}
