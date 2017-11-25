package tests;

import matrix.Matrix;
import matrix.MatrixException;
import matrix.operations.operators.Addition;
import matrix.operations.ParallelCalculator;
import matrix.operations.SerialCalculator;
import matrix.types.IntElem;
import matrix.types.MatrixElement;
import matrix.utils.MatrixUtils;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Sebi on 21-Oct-17.
 */
public class AddTest {
    @Test
    public void addInt1() throws MatrixException {
        IntElem n1 = new IntElem(1);
        IntElem n2 = new IntElem(2);
        IntElem n3 = new IntElem(4);
        IntElem n4 = new IntElem(5);
        MatrixElement[][] data = {{n1, n2}, {n3, n4}};
        IntElem n5 = new IntElem(1);
        IntElem n6 = new IntElem(2);
        IntElem n7 = new IntElem(4);
        IntElem n8 = new IntElem(5);
        MatrixElement[][] data2 = {{n5, n6}, {n7, n8}};
        Matrix m1 = new Matrix(2, 2);
        Matrix m2 = new Matrix(2, 2);
        m1.setData(data);
        m2.setData(data2);

        Matrix m3 = new Matrix(2,2);
        Matrix m4 = new Matrix(2, 2);
        ParallelCalculator parallelCalculator = new ParallelCalculator();
        SerialCalculator serialCalculator = new SerialCalculator();

        parallelCalculator.calculate(m1, m2, m3, 2, new Addition());
        serialCalculator.calculate(m1, m2, m4, new Addition());

        assertTrue(m3.equals(m4));
    }

    private void runSimulationInt(int nrThr, int nrRows, int nrCols) throws MatrixException {
        System.out.println("Int addition with " + nrThr + " threads");
        System.out.println("Matrix dimensions " + nrRows + "x" + nrCols);
        Matrix m1 = MatrixUtils.generateIntMatrix(nrRows, nrCols);
        Matrix m2 = MatrixUtils.generateIntMatrix(nrRows, nrCols);
        Matrix m3 = new Matrix(nrRows, nrCols);
        Matrix m4 = new Matrix(nrRows, nrCols);
        ParallelCalculator parallelCalculator = new ParallelCalculator();
        SerialCalculator serialCalculator = new SerialCalculator();


        parallelCalculator.calculate(m1, m2, m3, nrThr, new Addition());
        serialCalculator.calculate(m1, m2, m4, new Addition());

        assertTrue(m3.equals(m4));
    }

    @Test
    public void runSimulationIntCaller() throws MatrixException {
        for (int i = 2; i <= 8; i += 2){
            runSimulationInt(i, 1000, 1000);
            System.out.println("------------------------------------");
        }
    }

    private void runSimulationComplex(int nrThr, int nrRows, int nrCols) throws MatrixException {
        System.out.println("Complex add with " + nrThr + " threads");
        System.out.println("Matrix dimensions " + nrRows + "x" + nrCols);
        Matrix m1 = MatrixUtils.generateComplexMatrix(nrRows, nrCols);
        Matrix m2 = MatrixUtils.generateComplexMatrix(nrRows, nrCols);
        Matrix m3 = new Matrix(nrRows, nrCols);
        Matrix m4 = new Matrix(nrRows, nrCols);
        ParallelCalculator parallelCalculator = new ParallelCalculator();
        SerialCalculator serialCalculator = new SerialCalculator();


        parallelCalculator.calculate(m1, m2, m3, nrThr, new Addition());
        serialCalculator.calculate(m1, m2, m4, new Addition());

        assertTrue(m3.equals(m4));
    }

    @Test
    public void runSimulationComplexCaller() throws MatrixException {
        for (int i = 2; i <= 8; i += 2){
            runSimulationComplex(i, 1000, 1000);
            System.out.println("---------------------------------------");
        }
    }
}
