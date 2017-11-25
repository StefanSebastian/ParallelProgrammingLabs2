package matrix;

import matrix.operations.operators.*;
import matrix.operations.ParallelCalculator;
import matrix.operations.SerialCalculator;
import matrix.types.ComplexNumberElem;
import matrix.types.MatrixElement;
import matrix.utils.MatrixUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;
import java.util.function.BinaryOperator;

/**
 * Created by Sebi on 21-Oct-17.
 */
public class RunSimulation {

    public static void operationRun(int op, int r1, int c1, int r2, int c2, int nrTh, Matrix m1, Matrix m2, int type) throws MatrixException, IOException, URISyntaxException {
        SerialCalculator serialCalculator = new SerialCalculator();
        ParallelCalculator parallelCalculator = new ParallelCalculator();

        if (op == 1){
            Matrix m3 = new Matrix(r1, c1);
            Matrix m4 = new Matrix(r2, c2);

            BinaryOperator<MatrixElement> binaryOperator = (matrixElement, matrixElement2) -> {
                try {
                    return matrixElement.add(matrixElement2);
                } catch (MatrixException e) {
                    return null;
                }
            };

            System.out.println("Running serial operations");
            serialCalculator.calculate(m1, m2, m3, new GenericMatrixOperator(binaryOperator));
            System.out.println("Running parallel operations");
            parallelCalculator.calculate(m1, m2, m4, nrTh, new GenericMatrixOperator(binaryOperator));

            if (m3.equals(m4)){
                System.out.println("Valid result");
            } else {
                System.out.println("Invalid result");
            }

            System.out.println("Saving results");
            MatrixUtils.writeMatrix("E:\\Info\\anu3\\progr paralela\\labs\\ParallelProgrammingLabs\\lab2\\Lab2Java\\src\\matrix\\files\\ser.txt", m3);
            MatrixUtils.writeMatrix("E:\\Info\\anu3\\progr paralela\\labs\\ParallelProgrammingLabs\\lab2\\Lab2Java\\src\\matrix\\files\\par.txt", m4);


        } else if (op == 2){
            Matrix m3 = new Matrix(r1, c2);
            Matrix m4 = new Matrix(r1, c2);

            BinaryOperator<MatrixElement> operator = (matrixElement, matrixElement2) -> {
                try {
                    return matrixElement.multiply(matrixElement2);
                } catch (MatrixException e) {
                    return null;
                }
            };

            System.out.println("Running serial operations");
            serialCalculator.calculate(m1, m2, m3, new GenericMatrixOperator(operator));
            System.out.println("Running parallel operations");
            parallelCalculator.calculate(m1, m2, m4, nrTh, new GenericMatrixOperator(operator));

            if (m3.equals(m4)){
                System.out.println("Valid result");
            } else {
                System.out.println("Invalid result");
            }

            System.out.println("Saving results");
            MatrixUtils.writeMatrix("E:\\Info\\anu3\\progr paralela\\labs\\ParallelProgrammingLabs\\lab2\\Lab2Java\\src\\matrix\\files\\ser.txt", m3);
            MatrixUtils.writeMatrix("E:\\Info\\anu3\\progr paralela\\labs\\ParallelProgrammingLabs\\lab2\\Lab2Java\\src\\matrix\\files\\par.txt", m4);

        } else {
            Matrix m3 = new Matrix(r1, c2);
            Matrix m4 = new Matrix(r1, c2);

            MatrixElementOperator operator;
            if (type == 1){
                // operator = new PointComplexOperator();
                // alternative in generic version
                operator = new GenericMatrixOperator((a, b) -> {
                    ComplexNumberElem one = new ComplexNumberElem(1, 0);
                    try {
                        return one.divide(one.divide(a).add(one.divide(b)));
                    } catch (MatrixException e) {
                        return null;
                    }
                });
            } else if (type == 2){
                operator = new PointIntOperator();
            } else {
                operator = new PointDoubleOperator();
            }

            System.out.println("Running serial operations");
            serialCalculator.calculate(m1, m2, m3, operator);
            System.out.println("Running parallel operations");
            parallelCalculator.calculate(m1, m2, m4, nrTh, operator);

            if (m3.equals(m4)){
                System.out.println("Valid result");
            } else {
                System.out.println("Invalid result");
            }

            System.out.println("Saving results");
            MatrixUtils.writeMatrix("E:\\Info\\anu3\\progr paralela\\labs\\ParallelProgrammingLabs\\lab2\\Lab2Java\\src\\matrix\\files\\ser.txt", m3);
            MatrixUtils.writeMatrix("E:\\Info\\anu3\\progr paralela\\labs\\ParallelProgrammingLabs\\lab2\\Lab2Java\\src\\matrix\\files\\par.txt", m4);

        }
    }

    public static void intSim(int r1, int c1, int r2, int c2, int nrTh, int op, int type) throws MatrixException, IOException, URISyntaxException {
        Matrix m1;
        Matrix m2;
        System.out.println("Generating first matrix");
        m1 = MatrixUtils.generateIntMatrix(r1, c1);
        MatrixUtils.writeMatrix("E:\\Info\\anu3\\progr paralela\\labs\\ParallelProgrammingLabs\\lab2\\Lab2Java\\src\\matrix\\files\\mat1.txt", m1);

        System.out.println("Generating second matrix");
        m2 = MatrixUtils.generateIntMatrix(r2, c2);
        MatrixUtils.writeMatrix("E:\\Info\\anu3\\progr paralela\\labs\\ParallelProgrammingLabs\\lab2\\Lab2Java\\src\\matrix\\files\\mat2.txt", m2);
        operationRun(op, r1, c1, r2, c2, nrTh, m1, m2, type);
    }

    public static void doubleSim(int r1, int c1, int r2, int c2, int nrTh, int op, int type) throws MatrixException, IOException, URISyntaxException {
        Matrix m1;
        Matrix m2;
        System.out.println("Generating first matrix");
        m1 = MatrixUtils.generateDoubleMatrix(r1, c1);
        MatrixUtils.writeMatrix("E:\\Info\\anu3\\progr paralela\\labs\\ParallelProgrammingLabs\\lab2\\Lab2Java\\src\\matrix\\files\\mat1.txt", m1);

        System.out.println("Generating second matrix");
        m2 = MatrixUtils.generateDoubleMatrix(r2, c2);
        MatrixUtils.writeMatrix("E:\\Info\\anu3\\progr paralela\\labs\\ParallelProgrammingLabs\\lab2\\Lab2Java\\src\\matrix\\files\\mat2.txt", m2);
        operationRun(op, r1, c1, r2, c2, nrTh, m1, m2, type);
    }

    public static void complexSim(int r1, int c1, int r2, int c2, int nrTh, int op, int type) throws IOException, URISyntaxException, MatrixException {
        Matrix m1;
        Matrix m2;
        System.out.println("Generating first matrix");
        m1 = MatrixUtils.generateComplexMatrix(r1, c1);
        MatrixUtils.writeMatrix("E:\\Info\\anu3\\progr paralela\\labs\\ParallelProgrammingLabs\\lab2\\Lab2Java\\src\\matrix\\files\\mat1.txt", m1);

        System.out.println("Generating second matrix");
        m2 = MatrixUtils.generateComplexMatrix(r2, c2);
        MatrixUtils.writeMatrix("E:\\Info\\anu3\\progr paralela\\labs\\ParallelProgrammingLabs\\lab2\\Lab2Java\\src\\matrix\\files\\mat2.txt", m2);
        operationRun(op, r1, c1, r2, c2, nrTh, m1, m2, type);
    }

    public static void runSimulation() throws MatrixException, IOException, URISyntaxException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("First matrix dimensions: ");
        int r1 = scanner.nextInt();
        int c1 = scanner.nextInt();

        System.out.println("Second matrix dimensions: ");
        int r2 = scanner.nextInt();
        int c2 = scanner.nextInt();

        System.out.println("Operation 1.add 2.multiply 3.pointOp");
        int operation = scanner.nextInt();

        System.out.println("Type 1.complex 2.int 3.double");
        int type = scanner.nextInt();

        System.out.println("Nr threads: ");
        int nrTh = scanner.nextInt();

        if (type == 2){
            intSim(r1, c1, r2, c2, nrTh, operation, type);
        } else if (type == 1){
            complexSim(r1, c1, r2, c2, nrTh, operation, type);
        } else {
            doubleSim(r1, c1, r2, c2, nrTh, operation, type);
        }
    }

    public static void main(String[] args) {
        while (true){
            try {
                System.out.println("New simulation");
                runSimulation();
                System.out.println();
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

    }
}
