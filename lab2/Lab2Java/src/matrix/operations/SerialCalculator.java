package matrix.operations;

import matrix.Matrix;
import matrix.MatrixException;
import matrix.operations.operators.MatrixElementOperator;

/**
 * Created by Sebi on 21-Oct-17.
 */
public class SerialCalculator {

    public void calculate(Matrix m1, Matrix m2, Matrix res, MatrixElementOperator operator){
        // timestamp
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < res.getRows(); i++) {
            for (int j = 0; j < res.getCols(); j++) {
                try {
                    res.setElement(i, j, operator.execute(m1, m2, i, j));
                } catch (MatrixException e) {
                    e.printStackTrace();
                }
            }
        }

        // print time
        long endTime = System.currentTimeMillis();
        System.out.println("Serial operation ran for " + (endTime - startTime));
    }
}
