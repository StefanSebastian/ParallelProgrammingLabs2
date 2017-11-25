package shop.controller;

import shop.ShopException;
import shop.domain.Product;

import java.util.List;
import java.util.concurrent.Future;

/**
 * Created by Sebi on 25-Nov-17.
 */
public interface IController {
    List<Product> getProducts() throws ShopException;
    Future<OrderResult> buyProduct(Integer productCode, Integer quantity, String client) throws ShopException;
}
