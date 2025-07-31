package com.tejaswini;

public class User {
    private String username;
    private String password; // In real apps, never store plain text passwords!

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean authenticate(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    public String getUsername() {
        return username;
    }
}
