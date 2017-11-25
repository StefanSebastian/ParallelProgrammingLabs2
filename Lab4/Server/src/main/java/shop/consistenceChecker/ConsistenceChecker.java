package shop.consistenceChecker;

import shop.controller.IController;

/**
 * Created by Sebi on 25-Nov-17.
 */
public class ConsistenceChecker implements Runnable {
    IController controller;

    public ConsistenceChecker(IController controller){
        this.controller = controller;
    }

    @Override
    public void run() {

    }
}
