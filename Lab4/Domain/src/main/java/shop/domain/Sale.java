package shop.domain;

import java.util.Date;

/**
 * Created by Sebi on 25-Nov-17.
 */
public class Sale {
    private Integer saleCode;
    private Product product;
    private Integer quantity;
    private Date date;

    public Sale(Integer saleCode, Product product, Integer quantity, Date date) {
        this.saleCode = saleCode;
        this.product = product;
        this.quantity = quantity;
        this.date = date;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getSaleCode() {
        return saleCode;
    }

    public void setSaleCode(Integer saleCode) {
        this.saleCode = saleCode;
    }
}
