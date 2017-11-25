package shop.controller;

import shop.ShopException;
import shop.domain.Receipt;

import java.util.concurrent.Callable;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Sebi on 25-Nov-17.
 */
public class PayOperation implements Callable<OrderResult> {
    private Controller controller;
    private Integer productCode;
    private Integer quantity;
    private String client;
    private ReentrantLock lock;

    public PayOperation(Controller controller, Integer productCode, Integer quantity, String client, ReentrantLock lock) {
        this.controller = controller;
        this.productCode = productCode;
        this.quantity = quantity;
        this.client = client;
        this.lock = lock;
    }

    @Override
    public OrderResult call() throws Exception {
        lock.lock(); // acquire product lock ; no concurent operations on the same product

        // can have concurrent orders ; write lock will be acquired by report generator
        controller.getReadWriteLock().readLock().lock();
        OrderResult orderResult = new OrderResult();

        try {
            Receipt receipt = controller.buyProductTask(productCode, quantity, client);
            orderResult.setReceipt(receipt);
        } catch (ShopException e) {
            orderResult.setMessage(e.getMessage());
        } finally {
            controller.getReadWriteLock().readLock().unlock();
            lock.unlock();
        }
        return orderResult;
    }
}
