package shop.consistenceChecker;

import shop.ShopException;
import shop.controller.Controller;
import shop.controller.IController;

/**
 * Created by Sebi on 25-Nov-17.
 */
public class ConsistenceChecker implements Runnable {
    Controller controller;

    public ConsistenceChecker(Controller controller){
        this.controller = controller;
    }

    @Override
    public void run() {
        while (true){

            controller.getReadWriteLock().writeLock().lock();

            // current state
            try {
                controller.saveState();
            } catch (ShopException e) {
                e.printStackTrace();
            }

            controller.getReadWriteLock().writeLock().unlock();

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
