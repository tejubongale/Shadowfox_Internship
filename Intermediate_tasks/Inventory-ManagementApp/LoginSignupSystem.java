import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class LoginSignupSystem {

    private static final String USER_FILE = "users.txt";
    private static Map<String, String> userDatabase = new HashMap<>();

    public static void main(String[] args) {
        loadUsers();
        SwingUtilities.invokeLater(() -> showLoginWindow()); // 🔧 Ensure GUI runs in event thread
    }

    private static void loadUsers() {
        File file = new File(USER_FILE);
        if (!file.exists())
            return;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    userDatabase.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveUser(String username, String password) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_FILE, true))) {
            writer.write(username + ":" + password);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void showLoginWindow() {
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 200);
        frame.setLayout(new GridLayout(4, 2, 10, 10));

        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");
        JButton signupButton = new JButton("Sign Up");

        frame.add(new JLabel("Username:"));
        frame.add(usernameField);
        frame.add(new JLabel("Password:"));
        frame.add(passwordField);
        frame.add(loginButton);
        frame.add(signupButton);

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            if (userDatabase.containsKey(username) && userDatabase.get(username).equals(password)) {
                JOptionPane.showMessageDialog(frame, "Login successful!");
                frame.dispose();
                InventoryApp.main(null); // 👈 Open your Inventory App after login
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid username or password.");
            }
        });

        signupButton.addActionListener(e -> showSignupWindow());

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void showSignupWindow() {
        JFrame signupFrame = new JFrame("Sign Up");
        signupFrame.setSize(350, 200);
        signupFrame.setLayout(new GridLayout(4, 2, 10, 10));

        JTextField newUsernameField = new JTextField();
        JPasswordField newPasswordField = new JPasswordField();
        JButton createAccountButton = new JButton("Create Account");

        signupFrame.add(new JLabel("New Username:"));
        signupFrame.add(newUsernameField);
        signupFrame.add(new JLabel("New Password:"));
        signupFrame.add(newPasswordField);
        signupFrame.add(new JLabel(""));
        signupFrame.add(createAccountButton);

        createAccountButton.addActionListener(e -> {
            String username = newUsernameField.getText();
            String password = new String(newPasswordField.getPassword());

            if (userDatabase.containsKey(username)) {
                JOptionPane.showMessageDialog(signupFrame, "Username already exists.");
            } else {
                userDatabase.put(username, password);
                saveUser(username, password);
                JOptionPane.showMessageDialog(signupFrame, "Account created successfully!");
                signupFrame.dispose();
            }
        });

        signupFrame.setLocationRelativeTo(null);
        signupFrame.setVisible(true);
    }
}
