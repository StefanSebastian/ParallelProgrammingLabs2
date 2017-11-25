package lab1.tests;

import lab1.Matrix;
import lab1.matrixOperations.add.ParallelAddition;
import lab1.matrixOperations.add.SerialAddition;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by Sebi on 07-Oct-17.
 */
public class AdditionTest {

    @org.junit.Test
    public void calculateAdd1() throws Exception {
        double[][] a = {{1,2,3},{1,2,3},{1,2,3}};
        double[][] b = {{1,2,3},{1,2,3},{1,2,3}};
        Matrix matA = new Matrix(3, 3, a);
        Matrix matB = new Matrix(3, 3, b);

        double[][] res = {{2,4,6},{2,4,6},{2,4,6}};
        Matrix expectedRes = new Matrix(3, 3, res);

        SerialAddition serialAddition = new SerialAddition();
        Matrix actualResSerial = serialAddition.calculate(matA, matB, 0);

        ParallelAddition parallelAddition = new ParallelAddition();
        Matrix actualResParallel = parallelAddition.calculate(matA, matB, 2);

        assertTrue(expectedRes.equals(actualResSerial));
        assertTrue(expectedRes.equals(actualResParallel));
    }

    @org.junit.Test
    public void calculateAdd2() throws Exception {
        double[][] a = {{1,4,3},{4,4,4},{5,2,3},{8,2,6}};
        double[][] b = {{5,5,5},{1,1,1},{1,2,3},{10,1,10}};
        Matrix matA = new Matrix(4, 3, a);
        Matrix matB = new Matrix(4, 3, b);

        double[][] res = {{6,9,8},{5,5,5},{6,4,6},{18,3,16}};
        Matrix expectedRes = new Matrix(4, 3, res);

        SerialAddition serialAddition = new SerialAddition();
        Matrix actualResSerial = serialAddition.calculate(matA, matB, 0);

        ParallelAddition parallelAddition = new ParallelAddition();
        Matrix actualResParallel = parallelAddition.calculate(matA, matB, 2);

        assertTrue(expectedRes.equals(actualResSerial));
        assertTrue(expectedRes.equals(actualResParallel));
    }

    @org.junit.Test
    public void calculateAdd3() throws Exception {
        double[][] a = {{1,2,3},{1,2,3}};
        double[][] b = {{1,2,3},{1,2,3},{1,2,3}};
        Matrix matA = new Matrix(2, 3, a);
        Matrix matB = new Matrix(3, 3, b);

        Matrix expectedRes = null;

        SerialAddition serialAddition = new SerialAddition();
        Matrix actualResSerial = serialAddition.calculate(matA, matB, 0);

        ParallelAddition parallelAddition = new ParallelAddition();
        Matrix actualResParallel = parallelAddition.calculate(matA, matB, 2);

        assertTrue(actualResParallel == null);
        assertTrue(actualResSerial == null);
    }



}