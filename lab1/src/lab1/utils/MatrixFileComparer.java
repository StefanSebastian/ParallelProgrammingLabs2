package lab1.utils;

import lab1.Matrix;

/**
 * Created by Sebi on 07-Oct-17.
 */
public class MatrixFileComparer {

    /*
    Compares two matrixes stored in files
     */
    public Boolean compare(String path1, String path2){
        System.out.println("Comparing matrixes");
        MatrixFileReader matrixFileReader = new MatrixFileReader();
        Matrix a = matrixFileReader.getFromFile(path1);
        Matrix b = matrixFileReader.getFromFile(path2);

        return a.equals(b);
    }
}
