package lists.simulation;

import lists.SortedLinkedList;
import lists.coarse.SortedLinkedListCoarse;
import lists.fine.SortedLinkedListFine;
import lists.utils.InputGenerator;
import lists.utils.Logger;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sebi on 02-Nov-17.
 */
public class Main {

    /*
    Runs a simulation and returns the time
     */
    private static double runSimulation(SortedLinkedList list, int valuesT1, int valuesT2, int valuesT3, long sleep1, long sleep2, long sleep3, long sleep4) {
        Logger logger = new Logger("E:\\Info\\anu3\\progr paralela\\labs\\ParallelProgrammingLabs\\lab3\\src\\lists\\simulation\\ResultsPerformance.txt");
        logger.logOperation("Simulation started");

        Logger iterationLogger = new Logger("E:\\Info\\anu3\\progr paralela\\labs\\ParallelProgrammingLabs\\lab3\\src\\lists\\simulation\\ResultsIteration.txt");
        iterationLogger.logOperation("Simulation started");

        List<Double> add1 = InputGenerator.getValuesInRange(valuesT1, 0, 10);
        List<Double> add2 = InputGenerator.getValuesInRange(valuesT2, 0, 10);
        List<Double> delete = InputGenerator.getValuesFromLists(valuesT3, add1, add2);

        List<Thread> threads = new LinkedList<>();
        threads.add(new Thread(new Inserter("T1", list, add1, logger, sleep1)));
        threads.add(new Thread(new Inserter("T2", list, add2, logger, sleep2)));
        threads.add(new Thread(new Remover("T3", list, delete, logger, sleep3)));
        Printer printer = new Printer("T4", list, sleep4, logger, iterationLogger);
        Thread printerTh = new Thread(printer);


        // start time
        double start = System.currentTimeMillis();

        for (Thread t : threads){
            t.start();
        }
        printerTh.start();

        for (Thread t : threads){
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // end time ; dont count printer because it might be sleeping
        double duration = System.currentTimeMillis() - start;

        // stop the printer thread after the rest
        printer.setRunning(false);
        try {
            printerTh.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return duration;
    }

    public static void getTimeForCoarse(){
        double avgTime = 0;
        for (int i = 1; i <= 10; i++){
            double duration = runSimulation(new SortedLinkedListCoarse(),6000, 1000, 1000, 0, 0, 0, 20);
            System.out.println("Iteration" + i + " time : " + duration);
            avgTime += duration;
        }
        avgTime = avgTime / 10;
        System.out.println("Time for coarse grained: " + avgTime);
    }

    public static void getTimeForFine(){
        double avgTime = 0;
        for (int i = 1; i <= 10; i++){
            double duration = runSimulation(new SortedLinkedListFine(),6000, 1000, 1000, 0, 0, 0, 20);
            System.out.println("Iteration" + i + " time : " + duration);
            avgTime += duration;
        }
        avgTime = avgTime / 10;
        System.out.println("Time for fine grained: " + avgTime);
    }

    public static void main(String[] args) {
        getTimeForFine();
        getTimeForCoarse();

      //  runSimulation(new SortedLinkedListCoarse(), 10, 5, 7, 4, 4, 2, 12);

       // runSimulation(new SortedLinkedListFine(), 10, 5, 7, 4, 4, 2, 12);


       /*
        // Insertion time experiment
        SortedLinkedList list = new SortedLinkedListFine();
        Random random = new Random();

        double start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++){
            list.insert(random.nextInt() % 4);
        }
        double duration = System.currentTimeMillis() - start;
        System.out.println(duration);
        */
    }
}
