package matrix.operations.operators;

import matrix.Matrix;
import matrix.MatrixException;
import matrix.types.DoubleElem;
import matrix.types.MatrixElement;

/**
 * Created by Sebi on 24-Oct-17.
 */
public class PointDoubleOperator implements MatrixElementOperator {
    @Override
    public MatrixElement execute(Matrix m1, Matrix m2, int idx1, int idx2) throws MatrixException {
        double a = ((DoubleElem) m1.getElement(idx1, idx2)).getValue();
        double b = ((DoubleElem) m2.getElement(idx1, idx2)).getValue();
        double aux = 1 / (1 / a + 1 / b);
        return new DoubleElem(aux);
    }
}
