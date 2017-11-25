package matrix.operations.operators;

import matrix.Matrix;
import matrix.MatrixException;
import matrix.types.IntElem;
import matrix.types.MatrixElement;

/**
 * Created by Sebi on 22-Oct-17.
 */
public class PointIntOperator implements MatrixElementOperator {

    @Override
    public MatrixElement execute(Matrix m1, Matrix m2, int idx1, int idx2) throws MatrixException {
        int a = ((IntElem)m1.getElement(idx1, idx2)).getValue();
        int b = ((IntElem)m2.getElement(idx1, idx2)).getValue();
        double aux = (1 / (double)a) + (1/ (double)b);
        int res = (int)(1 / aux);
        return new IntElem(res);
    }
}
