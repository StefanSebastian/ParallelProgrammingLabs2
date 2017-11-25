package shop.domain;

/**
 * Created by Sebi on 25-Nov-17.
 */
public class Receipt {
    private String name;
    private Sale sale;
    private Double totalAmount;

    public Receipt(String name, Sale sale, Double totalAmount) {
        this.name = name;
        this.sale = sale;
        this.totalAmount = totalAmount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
