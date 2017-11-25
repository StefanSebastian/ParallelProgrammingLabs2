package matrix.types;

import matrix.MatrixException;

/**
 * Created by Sebi on 21-Oct-17.
 */
public interface MatrixElement {
    MatrixElement add(MatrixElement other) throws MatrixException;
    MatrixElement multiply(MatrixElement other) throws MatrixException;
    MatrixElement divide(MatrixElement other) throws MatrixException;
}
