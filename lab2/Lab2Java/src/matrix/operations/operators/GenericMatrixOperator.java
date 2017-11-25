package matrix.operations.operators;

import matrix.Matrix;
import matrix.MatrixException;
import matrix.types.MatrixElement;

import java.util.function.BinaryOperator;

/**
 * Created by Sebi on 25-Oct-17.
 *
 * Applies a binary operator on 2 elements
 */
public class GenericMatrixOperator implements MatrixElementOperator {

    private BinaryOperator<MatrixElement> binaryOperator;

    public GenericMatrixOperator(BinaryOperator<MatrixElement> binaryOperator) {
        this.binaryOperator = binaryOperator;
    }

    @Override
    public MatrixElement execute(Matrix m1, Matrix m2, int idx1, int idx2) throws MatrixException {
        return binaryOperator.apply(m1.getElement(idx1, idx2), m2.getElement(idx1, idx2));
    }
}
