package lab1.matrixOperations.multiply;

import lab1.Matrix;

/**
 * Created by Sebi on 07-Oct-17.
 */
public class ThreadMultiplyMat implements Runnable {
    private Matrix a;
    private Matrix b;
    private Matrix res;
    private int start;
    private int end;

    public ThreadMultiplyMat(Matrix a, Matrix b, Matrix res, int start, int end) {
        this.a = a;
        this.b = b;
        this.res = res;
        this.start = start;
        this.end = end;
    }

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
    public void run() {
        int rows = a.getRows();
        int cols = b.getCols();

        int startRow = start / cols;
        int startCol = start % cols;

        int endRow = end / cols;
        int endCol = end % cols;

       // System.out.println("Start row: " + startRow + " start col : " + startCol + " end row " + endRow + " endcol " + endCol);

        int i = startRow; int j = startCol;
        while (!(i == endRow && j == endCol)){

            // the operation
            res.getData()[i][j] = dotProduct(a, b, i, j);

            if (j < cols - 1){
                j++;
            } else {
                j = 0;
                i++;
            }
        }
    }
}
