
package com.example.finance.repositories;

import com.example.finance.models.Transaction;
import com.example.finance.models.Income;
import com.example.finance.models.Expense;

import java.util.ArrayList;
import java.util.List;

/**
 * Repository for Transaction data operations
 */
public class TransactionRepository extends BaseRepository<Transaction> {

    public TransactionRepository() {
        super("data/transactions.json");
    }

    @Override
    protected Class<Transaction> getType() {
        return Transaction.class;
    }

    // Get all incomes
    public List<Transaction> getIncomes() {
        List<Transaction> incomes = new ArrayList<>();
        for (Transaction t : items) {
            if (t instanceof Income) {
                incomes.add(t);
            }
        }
        return incomes;
    }

    // Get all expenses
    public List<Transaction> getExpenses() {
        List<Transaction> expenses = new ArrayList<>();
        for (Transaction t : items) {
            if (t instanceof Expense) {
                expenses.add(t);
            }
        }
        return expenses;
    }

    // Total income
    public double getTotalIncome() {
        double total = 0;
        for (Transaction t : getIncomes()) {
            total += t.getAmount();
        }
        return total;
    }

    // Total expense
    public double getTotalExpense() {
        double total = 0;
        for (Transaction t : getExpenses()) {
            total += t.getAmount();
        }
        return total;
    }

    // Net balance
    public double getNetBalance() {
        return getTotalIncome() - getTotalExpense();
    }

    // Get transaction by ID
    public Transaction getById(int id) {
        for (Transaction t : items) {
            if (t.getTransactionId() == id) {
                return t;
            }
        }
        return null;
    }

    // Delete transaction
    public void deleteTransaction(int id) {
        items.removeIf(t -> t.getTransactionId() == id);
        save();
    }
}
