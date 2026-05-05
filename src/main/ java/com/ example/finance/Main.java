package com.example.finance;

import javafx.application.Application;
import javafx.stage.Stage;

import com.example.finance.ui.LoginScreen;
import com.example.finance.models.*;
import com.example.finance.utils.JsonHandler;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Main Entry Point (JavaFX Launcher)
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        // تشغيل شاشة اللوجين
        new LoginScreen().start(stage);
    }

    /**
     * Demo Data (اختياري للتجربة)
     */
    public static void demoData() {

        File folder = new File("data");
        if (!folder.exists()) {
            folder.mkdir();
        }

        System.out.println("--- Personal Finance Management System ---");

        User user = new User(1, "Ahmed Ali", "ahmed@gmail.com", 1000.0);
        System.out.println("Initial Balance: " + user.getBalance());

        List<Transaction> transactions = new ArrayList<>();

        Income salary = new Income(101, 500.0, new Date(), "Freelance Project", "Software Development");
        transactions.add(salary);
        user.updateBalance(500.0);

        Expense lunch = new Expense(102, 200.0, new Date(), "Lunch", 3);
        transactions.add(lunch);
        user.updateBalance(-200.0);

        // ⚠️ مهم: ما تكتبش "data/" هنا
        JsonHandler.saveToFile("user_profile.json", user);
        JsonHandler.saveToFile("transactions_history.json", transactions);

        System.out.println("✔ Demo data saved.");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
