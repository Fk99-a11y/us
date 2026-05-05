package com.example.finance.controllers;

import com.example.finance.models.*;
import com.example.finance.utils.JsonHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Handles all finance operations between UI and Models
 */
public class FinanceController {

    private List<Transaction> transactions = new ArrayList<>();
    private List<Budget> budgets = new ArrayList<>();
    private User user;

    /**
     * Default constructor (used by AppContext)
     */
    public FinanceController() {
        // Dummy user (علشان البرنامج يشتغل)
        this.user = new User(1, "Ahmed Ali", "ahmed@gmail.com", 1000.0);

        // Dummy budgets
        budgets.add(new Budget(1, 1000, "May"));
        budgets.add(new Budget(2, 500, "May"));
    }

    /**
     * Optional constructor لو عايز تبعت user من بره
     */
    public FinanceController(User user) {
        this.user = user;
    }

    /**
     * Add income or expense transaction
     */
    public boolean addTransaction(double amount, String type, int categoryId, Date date, String notes) {

        if (amount <= 0) return false;

        Transaction transaction;

        if (type.equalsIgnoreCase("Income")) {

            transaction = new Income(
                    generateId(),
                    amount,
                    date,
                    notes,
                    "Manual Income"
            );

            user.updateBalance(amount);

        } else if (type.equalsIgnoreCase("Expense")) {

            transaction = new Expense(
                    generateId(),
                    amount,
                    date,
                    notes,
                    categoryId
            );

            user.updateBalance(-amount);

        } else {
            return false;
        }

        transactions.add(transaction);

        // Save data
        JsonHandler.saveToFile("data/transactions_history.json", transactions);
        JsonHandler.saveToFile("data/user_profile.json", user);

        return true;
    }

    /**
     * Get current balance
     */
    public double getBalance() {
        return user.getBalance();
    }

    /**
     * Get all transactions
     */
    public List<Transaction> getAllTransactions() {
        return transactions;
    }

    /**
     * Get budgets
     */
    public List<Budget> getBudgets() {
        return budgets;
    }

    /**
     * Add new budget
     */
    public void addBudget(Budget budget) {
        budgets.add(budget);
    }

    /**
     * Generate simple ID
     */
    private int generateId() {
        return transactions.size() + 1;
    }
}
