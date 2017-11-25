package matrix.utils;

import matrix.Matrix;
import matrix.types.ComplexNumberElem;
import matrix.types.DoubleElem;
import matrix.types.IntElem;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Sebi on 21-Oct-17.
 */
public class MatrixUtils {
    public static Matrix generateIntMatrix(int rows, int cols){
        Random random = new Random();

        Matrix m = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                double randomValue = 10 * random.nextDouble() + 1;
                int randomInt = (int)randomValue;
                IntElem nr = new IntElem(randomInt);
                m.setElement(i, j, nr);
            }
        }
        return m;
    }

    public static Matrix generateDoubleMatrix(int rows, int cols){
        Random random = new Random();

        Matrix m = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                double randomValue = 10 * random.nextDouble() + 1;
                DoubleElem nr = new DoubleElem(randomValue);
                m.setElement(i, j, nr);
            }
        }
        return m;
    }

    public static Matrix generateComplexMatrix(int rows, int cols){
        Random random = new Random();

        Matrix m = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                double r1 = 10 * random.nextDouble() + 1;
                double r2 = 10 * random.nextDouble() + 1;
                if (random.nextBoolean()){
                    r2 = -r2;
                }
                ComplexNumberElem nr = new ComplexNumberElem(r1, r2);
                m.setElement(i, j, nr);
            }
        }
        return m;
    }

    public static void writeMatrix(String path, Matrix m) throws IOException, URISyntaxException {
        File file = new File(path);
        FileWriter fileWriter = new FileWriter(file);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        String data = m.getRows() + " " + m.getCols() + "\n";
        printWriter.write(data);
        for (int i = 0; i < m.getRows(); i++){
            for (int j = 0; j < m.getCols(); j++){
                //data = data + m.getElement(i, j).toString();
                //data = data + " ";
                printWriter.write(m.getElement(i, j).toString());
                printWriter.write(" ");
            }
            //data = data + "\n";
            printWriter.write("\n");
        }

        printWriter.close();
    }

    public static Matrix readIntMatrix(String path) throws URISyntaxException, FileNotFoundException {
        File file = new File(path);
        Scanner scanner = new Scanner(file);

        int rows = scanner.nextInt();
        int cols = scanner.nextInt();

        Matrix matrix = new Matrix(rows, cols);

        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                int el = scanner.nextInt();
                matrix.setElement(i, j, new IntElem(el));
            }
        }

        scanner.close();

        return matrix;
    }

    private static ComplexNumberElem parseComplex(String complex){
        String[]tokens = complex.split("-|\\+");
        if (complex.contains("+")){
            double a = Double.parseDouble(tokens[0]);
            double b = Double.parseDouble(tokens[1]);
            return new ComplexNumberElem(a, b);
        } else {
            double a = Double.parseDouble(tokens[0]);
            double b = Double.parseDouble(tokens[1]);
            return new ComplexNumberElem(a, -b);
        }
    }

    public static Matrix readComplexMatrix(String path) throws URISyntaxException, FileNotFoundException {
        File file = new File(path);
        Scanner scanner = new Scanner(file);

        int rows = scanner.nextInt();
        int cols = scanner.nextInt();

        Matrix matrix = new Matrix(rows, cols);

        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                String el = scanner.next();
                matrix.setElement(i, j, parseComplex(el));
            }
        }

        scanner.close();

        return matrix;
    }
}
