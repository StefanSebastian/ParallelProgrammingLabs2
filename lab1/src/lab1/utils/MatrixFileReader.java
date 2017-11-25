package lab1.utils;

import lab1.Matrix;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Created by Sebi on 06-Oct-17.
 */
public class MatrixFileReader {
    /*
    Reads a matrix from the given file
     */
    public Matrix getFromFile(String path){
        try {
            Scanner scanner = new Scanner(new FileReader(path));
            //System.out.println("Started reading from file");

            int rows = scanner.nextInt();
            int cols = scanner.nextInt();
            double[][] data = new double[rows][cols];

            for (int i = 0; i < rows; i++){
                for (int j = 0; j < cols; j++){
                    //System.out.println(i + " " + j);
                    double val = scanner.nextDouble();
                    data[i][j] = val;
                }
            }

            //System.out.println("Finished reading from file");
            return new Matrix(rows, cols, data);

        } catch (FileNotFoundException e){
            System.out.println("File not found");
            return null;
        }
    }
}
