package shop.controller;

import shop.ShopException;
import shop.domain.Product;
import shop.domain.Sale;
import shop.domain.Stock;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * Created by Sebi on 25-Nov-17.
 */
public interface IController {
    List<Product> getProducts() throws ShopException;
    Future<OrderResult> buyProduct(Integer productCode, Integer quantity, String client) throws ShopException;
    void saveState() throws ShopException;
    Double getSold() throws ShopException;
    List<Sale> getSalesAfter(Date date) throws ShopException;
    Map<Integer, Stock> getStocks() throws ShopException;
}
