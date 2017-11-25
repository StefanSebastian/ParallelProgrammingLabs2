package lab1;

import lab1.matrixOperations.MatrixCalculator;
import lab1.matrixOperations.add.ParallelAddition;
import lab1.matrixOperations.add.SerialAddition;
import lab1.matrixOperations.multiply.ParallelMultiply;
import lab1.matrixOperations.multiply.SerialMultiply;
import lab1.utils.MatrixCalculatorException;
import lab1.utils.MatrixFileComparer;
import lab1.utils.MatrixFileWriter;
import lab1.utils.MatrixGenerator;

import java.util.Scanner;

/**
 * Created by Sebi on 06-Oct-17.
 */
public class Main {
    private static String filePrefix = "E:/Info/anu3/progr paralela/labs/ParallelProgrammingLabs/lab1/src/lab1/data/";

    public static void runSimulation(){
        MatrixFileWriter matrixFileWriter = new MatrixFileWriter();
        MatrixCalculator matrixCalculator = new MatrixCalculator();
        MatrixFileComparer matrixFileComparer = new MatrixFileComparer();
        MatrixGenerator matrixGenerator = new MatrixGenerator();


        matrixFileWriter.saveToFile(filePrefix + "mat1.txt", matrixGenerator.getMatrix(100, 100));
        matrixFileWriter.saveToFile(filePrefix + "mat2.txt", matrixGenerator.getMatrix(100, 100));
        try {
            matrixCalculator.calculate(new SerialMultiply(), filePrefix + "mat1.txt", filePrefix + "mat2.txt", filePrefix + "res1.txt", 0);
            matrixCalculator.calculate(new ParallelMultiply(), filePrefix + "mat1.txt", filePrefix + "mat2.txt", filePrefix + "res2.txt", 4);

            boolean valid = matrixFileComparer.compare(filePrefix + "res1.txt", filePrefix + "res2.txt");
            if (valid) {
                System.out.println("Result is valid");
            } else {
                System.out.println("Result is not valid");
            }
        } catch (MatrixCalculatorException e){
            System.out.println(e.getMessage());
        }
    }

    public static void runSimulation(Integer op, Integer n1, Integer m1, Integer n2, Integer m2, Integer nrThreads){
        MatrixFileWriter matrixFileWriter = new MatrixFileWriter();
        MatrixCalculator matrixCalculator = new MatrixCalculator();
        MatrixFileComparer matrixFileComparer = new MatrixFileComparer();
        MatrixGenerator matrixGenerator = new MatrixGenerator();


        matrixFileWriter.saveToFile(filePrefix + "mat1.txt", matrixGenerator.getMatrix(n1, m1));
        matrixFileWriter.saveToFile(filePrefix + "mat2.txt", matrixGenerator.getMatrix(n2, m2));
        try {

            if (op == 1){
                matrixCalculator.calculate(new SerialAddition(), filePrefix + "mat1.txt", filePrefix + "mat2.txt", filePrefix + "res1.txt", 0);
                matrixCalculator.calculate(new ParallelAddition(), filePrefix + "mat1.txt", filePrefix + "mat2.txt", filePrefix + "res2.txt", nrThreads);
            } else {
                matrixCalculator.calculate(new SerialMultiply(), filePrefix + "mat1.txt", filePrefix + "mat2.txt", filePrefix + "res1.txt", 0);
                matrixCalculator.calculate(new ParallelMultiply(), filePrefix + "mat1.txt", filePrefix + "mat2.txt", filePrefix + "res2.txt", nrThreads);
            }

            boolean valid = matrixFileComparer.compare(filePrefix + "res1.txt", filePrefix + "res2.txt");
            if (valid) {
                System.out.println("Result is valid");
            } else {
                System.out.println("Result is not valid");
            }
        } catch (MatrixCalculatorException e){
            System.out.println(e.getMessage());
        }
    }


    public static void main(String[] args) {
        ui();
    }

    public static void ui(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("1.addition 2.multiplication");
        Integer opt = scanner.nextInt();

        if (opt != 1 && opt != 2){
            System.out.println("Invalid input");
            return;
        }

        System.out.println("First matrix dimensions: ");
        Integer n1 = scanner.nextInt();
        Integer m1 = scanner.nextInt();

        System.out.println("Second matrix dimensions: ");
        Integer n2 = scanner.nextInt();
        Integer m2 = scanner.nextInt();

        System.out.println("Number of threads: ");
        Integer p = scanner.nextInt();

        runSimulation(opt, n1, m1, n2, m2, p);
    }
}
