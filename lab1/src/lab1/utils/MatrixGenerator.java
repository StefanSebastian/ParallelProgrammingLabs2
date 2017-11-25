package lab1.utils;

import lab1.Matrix;

import java.text.DecimalFormat;
import java.util.Random;

/**
 * Created by Sebi on 03-Oct-17.
 */
public class MatrixGenerator {
    /*
    Generates a random matrix of given dimensions
     */
    public Matrix getMatrix(int n, int m){
        double[][] matrix = new double[n][m];
        Random random = new Random();
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                double nr = random.nextDouble() * 100;
                DecimalFormat df = new DecimalFormat("#.##");
                nr = Double.valueOf(df.format(nr));
                matrix[i][j] = nr;
            }
        }
        return new Matrix(n, m, matrix);
    }
}
