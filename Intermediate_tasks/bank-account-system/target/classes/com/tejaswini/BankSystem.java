package com.tejaswini;

import java.util.*;

public class BankSystem {
    private Map<String, String> credentials = new HashMap<>();
    private Map<String, BankAccount> accounts = new HashMap<>();
    private Scanner sc = new Scanner(System.in);

    public void start() {
        System.out.println(" Welcome to Multi-User Bank System");
        loadUsers();

        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.print("Choose option: ");
        int choice = sc.nextInt();
        sc.nextLine(); // consume newline

        if (choice == 1) {
            login();
        } else if (choice == 2) {
            register();
        } else {
            System.out.println(" Invalid choice. Exiting...");
        }
    }

    private void loadUsers() {
        credentials.put("teju", "teju123");
        accounts.put("teju", new BankAccount("Tejaswini", 1500));
    }

    private void login() {
        System.out.print("Enter username: ");
        String user = sc.nextLine();
        System.out.print("Enter password: ");
        String pass = sc.nextLine();

        if (credentials.containsKey(user) && credentials.get(user).equals(pass)) {
            System.out.println("✅ Login successful!");
            userMenu(accounts.get(user));
        } else {
            System.out.println(" Invalid credentials.");
        }
    }

    private void register() {
        System.out.print("Choose username: ");
        String newUser = sc.nextLine();
        if (credentials.containsKey(newUser)) {
            System.out.println(" Username already exists.");
            return;
        }
        System.out.print("Set password: ");
        String newPass = sc.nextLine();
        System.out.print("Initial deposit: ");
        double initial = sc.nextDouble();
        sc.nextLine(); // consume newline

        credentials.put(newUser, newPass);
        accounts.put(newUser, new BankAccount(newUser, initial));

        System.out.println(" Registration successful. Logging you in...");
        userMenu(accounts.get(newUser));
    }

    private void userMenu(BankAccount account) {
        while (true) {
            System.out.println("\n Welcome, " + account.getName());
            System.out.println(
                    "1. Deposit\n2. Withdraw\n3. Check Balance\n4. Transaction History\n5. Export to File\n6. Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter deposit amount: ");
                    account.deposit(sc.nextDouble());
                    break;
                case 2:
                    System.out.print("Enter withdraw amount: ");
                    account.withdraw(sc.nextDouble());
                    break;
                case 3:
                    System.out.println(" Balance: ₹" + account.getBalance());
                    break;
                case 4:
                    System.out.println(" Transaction History:");
                    for (String txn : account.getTransactionHistory()) {
                        System.out.println(txn);
                    }
                    break;
                case 5:
                    account.exportTransactionHistory();
                    break;
                case 6:
                    System.out.println(" Goodbye, " + account.getName() + "!");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
