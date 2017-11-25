package shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.ShopException;
import shop.domain.Product;
import shop.domain.Receipt;
import shop.domain.Stock;
import shop.repository.IRepository;

import java.util.HashMap;
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
public class Controller implements IController {
    // use a lock for each product
    private Map<Integer, ReentrantLock> locks;

    ExecutorService executorService = Executors.newFixedThreadPool(8);

    private void initialize(){
        try {
            List<Product> products = repository.getProducts();
            locks = new HashMap<>();
            for (Product product : products){
                locks.put(product.getProductCode(), new ReentrantLock());
            }
        } catch (ShopException e) {
            e.printStackTrace();
        }
    }

    public Controller(){
        initialize();
    }

    public Controller(IRepository repository){
        this.repository = repository;
        initialize();
    }

    private IRepository repository;

    public void setRepository(IRepository repository){
        this.repository = repository;
    }

    @Override
    public List<Product> getProducts() throws ShopException {
        return repository.getProducts();
    }

    @Override
    public Future<OrderResult> buyProduct(Integer productCode, Integer quantity, String client) throws ShopException {
        return executorService.submit(new PayOperation(this, productCode, quantity, client, locks.get(productCode)));
    }

    public Receipt buyProductTask(Integer productCode, Integer quantity, String client) throws ShopException {
        //gets stock ; will always get correct value because we are locked on product
        Stock stock = repository.getStockForProduct(productCode);
        if (stock.getQuantity() < quantity){
            throw new ShopException("Not enough products on stock");
        }
        stock.setQuantity(stock.getQuantity() - quantity);
        repository.setStockForProduct(productCode, stock); // only the current thread can modify the stock for this product

        // add sale and add receipt ; synchronized methods
        Integer saleId = repository.addSale(productCode, quantity);
        Receipt receipt = repository.addReceipt(saleId, client);

        // uses lock on repo
        repository.incrementSold(receipt);
        return receipt;
    }
}
