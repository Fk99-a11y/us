
package com.example.finance.models;

import java.util.Date;

public abstract class Transaction {

    protected int transactionId;
    protected double amount;
    protected Date date;
    protected String notes;

    public Transaction(int transactionId, double amount, Date date, String notes) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.date = date;
        this.notes = notes;
    }

    public double getAmount() {
        return amount;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public Date getDate() {
        return date;
    }

    public abstract String getDetails();
}
