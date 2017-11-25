package lab1.utils;

import lab1.Matrix;

import java.io.*;

/**
 * Created by Sebi on 06-Oct-17.
 */
public class MatrixFileWriter {
    /*
    Saves a matrix to the given file
     */
    public void saveToFile(String path, Matrix matrix){
        try {
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(path));
            //System.out.println("Started writing to file");

            fileWriter.write(matrix.getRows() + " " + matrix.getCols() + "\n");
            for (int i = 0; i < matrix.getRows(); i++){
                for (int j = 0; j < matrix.getCols(); j++){
                   // System.out.println(i + " " + j);
                    fileWriter.write(matrix.getData()[i][j] + " ");
                }
                fileWriter.write("\n");
            }

           // System.out.println("Finished writing to file");
            fileWriter.close();

        } catch (IOException e){
            System.out.println("Cant write to file");
        }
    }
}
