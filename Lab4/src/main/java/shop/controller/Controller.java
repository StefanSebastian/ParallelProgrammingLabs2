package shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.ShopException;
import shop.domain.Product;
import shop.domain.Receipt;
import shop.repository.IRepository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Sebi on 25-Nov-17.
 */
@Component
public class Controller implements IController {
    // use a lock for each product
    private Map<Integer, ReentrantLock> locks;

    ExecutorService executorService = Executors.newFixedThreadPool(8);

    public Controller(){
        try {
            List<Product> products = repository.getProducts();
            for (Product product : products){
                locks.put(product.getProductCode(), new ReentrantLock());
            }
        } catch (ShopException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    private IRepository repository;

    @Override
    public List<Product> getProducts() throws ShopException {
        return repository.getProducts();
    }

    @Override
    public OrderResult buyProduct(Integer productCode, Integer quantity, String client) throws ShopException {
        Future<OrderResult> orderResult =
            executorService.submit(new PayOperation(this, productCode, quantity, client, locks.get(productCode)));
        try {
            return orderResult.get();
        } catch (InterruptedException | ExecutionException e) {
           return null;
        }
    }

    public Receipt buyProductTask(Integer productCode, Integer quantity, String client) throws ShopException {

        return null;
    }





}
