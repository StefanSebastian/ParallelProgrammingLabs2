package shop.repository;
import org.springframework.stereotype.Component;
import shop.ShopException;
import shop.domain.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sebi on 25-Nov-17.
 */
@Component
public class Repository implements IRepository{
    private Sold sold;
    private List<Product> products;
    private List<Sale> sales;
    private List<Stock> stocks;
    private List<Receipt> receipts;

    private void populateProducts(){
        products.add(new Product(1, "Masina", 10d, "a"));
        products.add(new Product(2, "Bicicleta", 14d, "a"));
        products.add(new Product(3, "Tren", 20d, "a"));
    }

    public Repository() {
        sold = new Sold(0d);
        products = new ArrayList<>();
        sales = new ArrayList<>();
        stocks = new ArrayList<>();
        receipts = new ArrayList<>();
        populateProducts();
    }

    public List<Product> getProducts() throws ShopException {
        return products;
    }
}
