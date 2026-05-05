
package com.example.finance.models;

public class User {

    private int userId;
    private String name;
    private String email;
    private double totalBalance;

    public User(int userId, String name, String email, double totalBalance) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.totalBalance = totalBalance;
    }

    public void updateBalance(double amount) {
        this.totalBalance += amount;
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public double getBalance() {
        return totalBalance;
    }
}
