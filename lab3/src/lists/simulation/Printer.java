package lists.simulation;

import lists.IIterator;
import lists.SortedLinkedList;
import lists.utils.Logger;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sebi on 02-Nov-17.
 */
public class Printer implements Runnable {
    private String name;
    private SortedLinkedList list;
    private Long sleepTime;
    private Logger logger;
    private Logger iterationLogger;

    private volatile boolean running = true;

    public Printer(String name, SortedLinkedList list, Long sleepTime, Logger logger, Logger iterationLogger) {
        this.name = name;
        this.list = list;
        this.sleepTime = sleepTime;
        this.logger = logger;
        this.iterationLogger = iterationLogger;
    }

    @Override
    public void run() {
        int itNumber = 0;
        while(running) {
            itNumber++;

            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            logger.logOperation(name + " iteration start");

            List<Double> itRes = new LinkedList<>();
            IIterator iterator = list.getIterator();
            while (iterator.isValid()) {
                itRes.add(iterator.getElement());
                iterator.getNext();
            }

            logger.logOperation(name + " iteration ended ; " + itRes);
            iterationLogger.logOperation("iteration " + itNumber + " res : " + itRes);
        }
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
