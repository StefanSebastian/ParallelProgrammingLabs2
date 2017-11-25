package shop.repository;

import shop.ShopException;
import shop.domain.Product;
import shop.domain.Receipt;
import shop.domain.Stock;

import java.util.List;

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
}
