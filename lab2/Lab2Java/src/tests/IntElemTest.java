package tests;
import matrix.types.IntElem;

import static org.junit.Assert.*;

/**
 * Created by Sebi on 21-Oct-17.
 */
public class IntElemTest {
    @org.junit.Test
    public void add() throws Exception {
        IntElem n1 = new IntElem(1);
        IntElem n2 = new IntElem(3);
        IntElem n3 = (IntElem) n1.add(n2);
        assertTrue(n3.getValue() == 4);
    }

    @org.junit.Test
    public void multiply() throws Exception {
        IntElem n1 = new IntElem(2);
        IntElem n2 = new IntElem(3);
        IntElem n3 = (IntElem) n1.multiply(n2);
        assertTrue(n3.getValue() == 6);
    }

}