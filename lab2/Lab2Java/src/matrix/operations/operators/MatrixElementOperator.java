package matrix.operations.operators;

import matrix.Matrix;
import matrix.MatrixException;
import matrix.types.MatrixElement;

/**
 * Created by Sebi on 21-Oct-17.
 */
public interface MatrixElementOperator {
    MatrixElement execute(Matrix m1, Matrix m2, int idx1, int idx2) throws MatrixException;
}
