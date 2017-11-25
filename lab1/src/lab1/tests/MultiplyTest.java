package lab1.tests;

import lab1.Matrix;
import lab1.matrixOperations.multiply.ParallelMultiply;
import lab1.matrixOperations.multiply.SerialMultiply;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by Sebi on 07-Oct-17.
 */
public class MultiplyTest {

    @org.junit.Test
    public void testMultiply1() throws Exception {
        double[][] a = {{1,7},{2,4}};
        double[][] b = {{3,3},{5,2}};
        Matrix matA = new Matrix(2, 2, a);
        Matrix matB = new Matrix(2, 2, b);

        double[][] res = {{38,17},{26,14}};
        Matrix expectedRes = new Matrix(2, 2, res);

        SerialMultiply serialMultiply = new SerialMultiply();
        ParallelMultiply parallelMultiply = new ParallelMultiply();

        Matrix actualSer = serialMultiply.calculate(matA, matB, 0);
        Matrix actualPar = parallelMultiply.calculate(matA, matB, 2);

        assertTrue(expectedRes.equals(actualPar));
        assertTrue(expectedRes.equals(actualSer));
    }

    @org.junit.Test
    public void testMultiply2() throws Exception {
        double[][] a = {{1,2},{3,4},{5,6}};
        double[][] b = {{1,3},{2,4}};
        Matrix matA = new Matrix(3, 2, a);
        Matrix matB = new Matrix(2, 2, b);

        double[][] res = {{5,11},{11,25},{17,39}};
        Matrix expectedRes = new Matrix(3, 2, res);

        SerialMultiply serialMultiply = new SerialMultiply();
        ParallelMultiply parallelMultiply = new ParallelMultiply();

        Matrix actualSer = serialMultiply.calculate(matA, matB, 0);
        Matrix actualPar = parallelMultiply.calculate(matA, matB, 2);

        assertTrue(expectedRes.equals(actualPar));
        assertTrue(expectedRes.equals(actualSer));
    }

    @org.junit.Test
    public void testMultiply3() throws Exception {
        double[][] a = {{1,2},{3,4},{5,6}};
        double[][] b = {{1,3},{2,4},{2,2}};
        Matrix matA = new Matrix(3, 2, a);
        Matrix matB = new Matrix(3, 2, b);

        Matrix expectedRes = null;

        SerialMultiply serialMultiply = new SerialMultiply();
        ParallelMultiply parallelMultiply = new ParallelMultiply();

        Matrix actualSer = serialMultiply.calculate(matA, matB, 0);
        Matrix actualPar = parallelMultiply.calculate(matA, matB, 2);

        assertTrue(actualPar == null);
        assertTrue(actualSer == null);
    }
}
