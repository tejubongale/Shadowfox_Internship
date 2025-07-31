package com.tejaswini;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BankAccount {
    private String accountHolder;
    private double balance;
    private List<String> transactionHistory;

    public BankAccount(String accountHolder, double initialBalance) {
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
        transactionHistory.add("Account created with ₹" + initialBalance);
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited ₹" + amount);
            System.out.println(" Deposited ₹" + amount);
        } else {
            System.out.println(" Invalid deposit amount.");
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrew ₹" + amount);
            System.out.println(" Withdrew ₹" + amount);
            return true;
        } else {
            System.out.println(" Insufficient balance or invalid amount.");
            return false;
        }
    }

    public double getBalance() {
        return balance;
    }

    public String getName() {
        return accountHolder;
    }

    public List<String> getTransactionHistory() {
        return transactionHistory;
    }

    public void exportTransactionHistory() {
        String filename = accountHolder + "_history.txt";
        try (FileWriter writer = new FileWriter(filename)) {
            for (String txn : transactionHistory) {
                writer.write(txn + "\n");
            }
            System.out.println(" Transaction history exported to: " + filename);
        } catch (IOException e) {
            System.out.println(" Error writing file: " + e.getMessage());
        }
    }
}
