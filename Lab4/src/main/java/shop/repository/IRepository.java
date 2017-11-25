package shop.repository;

import shop.ShopException;
import shop.domain.Product;

import java.util.List;

/**
 * Created by Sebi on 25-Nov-17.
 */
public interface IRepository {
    List<Product> getProducts() throws ShopException;
}
