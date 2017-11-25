package tests;

import matrix.Matrix;
import matrix.types.IntElem;
import matrix.types.MatrixElement;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Sebi on 21-Oct-17.
 */
public class MatrixTest {
    @Test
    public void equals() throws Exception {
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

        assertTrue(m1.equals(m2));
    }

}