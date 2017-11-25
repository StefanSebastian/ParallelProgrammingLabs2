package shop.domain;

/**
 * Created by Sebi on 25-Nov-17.
 */
public class Receipt {
    private Integer receiptCode;
    private String name;
    private Sale sale;
    private Double totalAmount;

    public Receipt(Integer receiptCode, String name, Sale sale, Double totalAmount) {
        this.receiptCode = receiptCode;
        this.name = name;
        this.sale = sale;
        this.totalAmount = totalAmount;
    }

    public Integer getReceiptCode() {
        return receiptCode;
    }

    public void setReceiptCode(Integer receiptCode) {
        this.receiptCode = receiptCode;
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

    @Override
    public String toString() {
        return "Receipt{" +
                "name='" + name + '\'' +
                ", sale=" + sale +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
