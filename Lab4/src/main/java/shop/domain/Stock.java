package shop.domain;

/**
 * Created by Sebi on 25-Nov-17.
 */
public class Stock {
    private Integer stockCode;
    private Product product;
    private Integer quantity;

    public Stock() {
    }

    public Stock(Integer stockId, Product product, Integer quantity) {
        this.stockCode = stockId;
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getStockCode() {
        return stockCode;
    }

    public void setStockCode(Integer stockCode) {
        this.stockCode = stockCode;
    }
}
