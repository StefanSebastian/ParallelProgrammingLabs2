package shop.controller;

import shop.domain.Receipt;

/**
 * Created by Sebi on 25-Nov-17.
 */
public class OrderResult {
    private Receipt receipt;
    private String message;

    public OrderResult() {
    }

    public OrderResult(Receipt receipt, String message) {
        this.receipt = receipt;
        this.message = message;
    }

    public Receipt getReceipt() {
        return receipt;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
