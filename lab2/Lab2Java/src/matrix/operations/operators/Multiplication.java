package matrix.operations.operators;

import matrix.Matrix;
import matrix.MatrixException;
import matrix.types.MatrixElement;

/**
 * Created by Sebi on 21-Oct-17.
 */
public class Multiplication implements MatrixElementOperator {
    @Override
    public MatrixElement execute(Matrix m1, Matrix m2, int idx1, int idx2) throws MatrixException {
        if (m1.getCols() != m2.getRows()){
            throw new MatrixException("Invalid input");
        }
        MatrixElement product = m1.getElement(idx1, 0).multiply(m2.getElement(0, idx2));
        for (int i = 1; i < m1.getCols(); i++){
            product.add(m1.getElement(idx1, i).multiply(m2.getElement(i, idx2)));
        }
        return product;
    }
}
