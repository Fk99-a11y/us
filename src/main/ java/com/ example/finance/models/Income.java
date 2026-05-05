
package com.example.finance.models;

import java.util.Date;

public class Income extends Transaction {

    private String source;

    public Income(int transactionId, double amount, Date date, String notes, String source) {
        super(transactionId, amount, date, notes);
        this.source = source;
    }

    public String getSource() {
        return source;
    }

    @Override
    public String getDetails() {
        return "Income Source: " + source +
               " | Amount: " + amount +
               " | Date: " + date;
    }
}
