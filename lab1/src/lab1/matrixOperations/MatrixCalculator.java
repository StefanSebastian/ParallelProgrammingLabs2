package lab1.matrixOperations;

import lab1.utils.MatrixCalculatorException;
import lab1.utils.MatrixFileReader;
import lab1.utils.MatrixFileWriter;
import lab1.Matrix;

/**
 * Created by Sebi on 06-Oct-17.
 */
public class MatrixCalculator {

    /*
    Reads 2 matrixes from files
    applies the operation
    saves result in the given file
     */
    public void calculate(IMatrixOperation operation, String pathToFirst, String pathToSecond, String pathToResult, Integer nrThreads) throws MatrixCalculatorException {
        MatrixFileReader matrixFileReader = new MatrixFileReader();
        MatrixFileWriter matrixFileWriter = new MatrixFileWriter();

        Matrix a =  matrixFileReader.getFromFile(pathToFirst);
        Matrix b = matrixFileReader.getFromFile(pathToSecond);

        if (a == null || b == null){
            throw new MatrixCalculatorException("Invalid input");
        }

        Matrix c = operation.calculate(a, b, nrThreads);

        if (c == null){
            throw new MatrixCalculatorException("Invalid input");
        }
        matrixFileWriter.saveToFile(pathToResult, c);
    }
}
