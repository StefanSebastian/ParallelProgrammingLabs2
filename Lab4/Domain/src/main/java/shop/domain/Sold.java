package shop.domain;

/**
 * Created by Sebi on 25-Nov-17.
 */
public class Sold {
    private Double amount;

    public Sold(Double amount) {
        this.amount = amount;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
