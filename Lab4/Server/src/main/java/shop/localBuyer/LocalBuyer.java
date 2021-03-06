package shop.localBuyer;

import shop.ShopException;
import shop.controller.IController;
import shop.controller.OrderResult;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by Sebi on 25-Nov-17.
 */
public class LocalBuyer implements Runnable {
    private IController controller;
    private Random random;

    public LocalBuyer(IController controller){
        this.controller = controller;
        this.random = new Random();
    }

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Integer prodCode = random.nextInt() % 3;
            if (prodCode < 0){
                prodCode = -prodCode;
            }
            prodCode++;

            Integer quantity = random.nextInt() % 5;
            if (quantity < 0){
                quantity = -quantity;
            }
            quantity++;

            String name = Thread.currentThread().getName();

            try {
                System.out.println("Trying to buy : " + prodCode + " , " + quantity + " , " + name);
                Future<OrderResult> or = controller.buyProduct(prodCode, quantity, name);
                OrderResult orderResult = or.get();
                if (orderResult.getMessage() != null){
                    System.out.println(orderResult.getMessage());
                } else if (orderResult.getReceipt() != null){
                    System.out.println(orderResult.getReceipt());
                }
            } catch (ShopException | InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
