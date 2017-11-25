package shop.controller;

import shop.ShopException;
import shop.domain.Product;

import java.util.List;

/**
 * Created by Sebi on 25-Nov-17.
 */
public interface IController {
    List<Product> getProducts() throws ShopException;
    OrderResult buyProduct(Integer productCode, Integer quantity, String client) throws ShopException;
}
