package matrix.operations.operators;

import matrix.Matrix;
import matrix.MatrixException;
import matrix.types.ComplexNumberElem;
import matrix.types.MatrixElement;

/**
 * Created by Sebi on 22-Oct-17.
 */
public class PointComplexOperator implements MatrixElementOperator {
    @Override
    public MatrixElement execute(Matrix m1, Matrix m2, int idx1, int idx2) throws MatrixException {
        ComplexNumberElem a = (ComplexNumberElem)m1.getElement(idx1, idx2);
        ComplexNumberElem b = (ComplexNumberElem)m2.getElement(idx1, idx2);
        ComplexNumberElem one = new ComplexNumberElem(1, 0);
        return one.divide(one.divide(a).add(one.divide(b)));
    }
}
