package lists.simulation;

import lists.SortedLinkedList;
import lists.utils.Logger;

import java.util.List;
import java.util.Random;

/**
 * Created by Sebi on 02-Nov-17.
 */
public class Inserter implements Runnable {
    private String name;
    private SortedLinkedList list;
    private List<Double> valuesToAdd;
    private Logger logger;
    private Long sleepTime;

    public Inserter(String name, SortedLinkedList list, List<Double> valuesToAdd, Logger logger, Long sleepTime){
        this.name = name;
        this.list = list;
        this.valuesToAdd = valuesToAdd;
        this.logger = logger;
        this.sleepTime = sleepTime;
    }

    @Override
    public void run() {

        int i = 0;
        for (double nr : valuesToAdd){
            i++;
            logger.logOperation(name + " insertion#" + i + " started " + nr);
            list.insert(nr);
            logger.logOperation(name + " insertion#" + i + " ended " + nr);

            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
