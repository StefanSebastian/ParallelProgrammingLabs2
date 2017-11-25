package lists.simulation;

import lists.SortedLinkedList;
import lists.utils.Logger;

import java.util.List;
import java.util.Random;

/**
 * Created by Sebi on 02-Nov-17.
 */
public class Remover implements Runnable {
    private String name;
    private SortedLinkedList list;
    private List<Double> valuesToRemove;
    private Logger logger;
    private Long sleepTime;

    public Remover(String name, SortedLinkedList list, List<Double> valuesToAdd, Logger logger, Long sleepTime){
        this.name = name;
        this.list = list;
        this.valuesToRemove = valuesToAdd;
        this.logger = logger;
        this.sleepTime = sleepTime;
    }

    @Override
    public void run() {
        int i = 0;
        for (double nr : valuesToRemove){
            i++;
            logger.logOperation(name + " deletion#" + i + " started " + nr);
            list.delete(nr);
            logger.logOperation(name + " deletion#" + i + " ended " + nr);

            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
