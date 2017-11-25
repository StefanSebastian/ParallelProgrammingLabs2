package shop.repository;

import shop.ShopException;
import shop.domain.Product;
import shop.domain.Receipt;
import shop.domain.Sale;
import shop.domain.Stock;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Sebi on 25-Nov-17.
 */
public interface IRepository {
    List<Product> getProducts() throws ShopException;
    Stock getStockForProduct(Integer productId) throws ShopException;
    void setStockForProduct(Integer productId, Stock stock) throws ShopException;
    Integer addSale(Integer productId, Integer quantity) throws ShopException;
    Receipt addReceipt(Integer saleId, String name) throws ShopException;
    void saveState() throws ShopException;
    void incrementSold(Receipt receipt) throws ShopException;
    Double getSold() throws ShopException;
    List<Sale> getSalesAfter(Date date) throws ShopException;
    Map<Integer, Stock> getStocks() throws ShopException;
}
