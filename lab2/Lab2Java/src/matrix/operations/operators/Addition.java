package matrix.operations.operators;

import matrix.Matrix;
import matrix.MatrixException;
import matrix.types.MatrixElement;

/**
 * Created by Sebi on 21-Oct-17.
 */
public class Addition implements MatrixElementOperator {
    @Override
    public MatrixElement execute(Matrix m1, Matrix m2, int idx1, int idx2) throws MatrixException {
        if (m1.getCols() != m2.getCols() || m1.getRows() != m2.getRows()){
            throw new MatrixException("Invalid input");
        }
        MatrixElement el1 = m1.getElement(idx1, idx2);
        MatrixElement el2 = m2.getElement(idx1, idx2);
        return el1.add(el2);
    }
}
