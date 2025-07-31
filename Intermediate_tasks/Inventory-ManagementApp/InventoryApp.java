import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class InventoryApp {
    private final InventoryManager manager = new InventoryManager();
    private final DefaultTableModel tableModel = new DefaultTableModel(new String[] { "Name", "Quantity", "Price" }, 0);

    public InventoryApp() {
        JFrame frame = new JFrame(" Inventory Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 550);
        frame.getContentPane().setBackground(new Color(240, 248, 255));
        frame.setLayout(new BorderLayout(10, 10));

        JPanel topPanel = new JPanel(new BorderLayout(5, 5));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        topPanel.setBackground(new Color(225, 245, 254));

        JTextField searchField = new JTextField();
        searchField.setFont(new Font("Arial", Font.PLAIN, 16));
        JButton searchButton = new JButton(" Search");
        searchButton.setBackground(new Color(129, 212, 250));
        searchButton.setFont(new Font("Arial", Font.BOLD, 14));

        topPanel.add(searchField, BorderLayout.CENTER);
        topPanel.add(searchButton, BorderLayout.EAST);

        JTable table = new JTable(tableModel);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        table.setRowHeight(24);
        JScrollPane tablePane = new JScrollPane(table);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        formPanel.setBackground(new Color(232, 245, 233));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel nameLabel = new JLabel("Item Name:");
        JTextField nameField = new JTextField(15);
        JLabel quantityLabel = new JLabel("Quantity:");
        JTextField quantityField = new JTextField(8);
        JLabel priceLabel = new JLabel("Price:");
        JTextField priceField = new JTextField(10);

        JButton addButton = new JButton("Add");
        JButton updateButton = new JButton(" Update");
        JButton deleteButton = new JButton(" Delete");

        addButton.setBackground(new Color(144, 238, 144));
        updateButton.setBackground(new Color(255, 213, 79));
        deleteButton.setBackground(new Color(239, 83, 80));

        Font font = new Font("Segoe UI", Font.BOLD, 14);
        addButton.setFont(font);
        updateButton.setFont(font);
        deleteButton.setFont(font);

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(nameLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(nameField, gbc);

        gbc.gridx = 2;
        formPanel.add(quantityLabel, gbc);

        gbc.gridx = 3;
        formPanel.add(quantityField, gbc);

        gbc.gridx = 4;
        formPanel.add(priceLabel, gbc);

        gbc.gridx = 5;
        formPanel.add(priceField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(addButton, gbc);

        gbc.gridx = 3;
        formPanel.add(updateButton, gbc);

        gbc.gridx = 5;
        formPanel.add(deleteButton, gbc);

        addButton.addActionListener(e -> {
            try {
                String name = nameField.getText();
                int qty = Integer.parseInt(quantityField.getText());
                double price = Double.parseDouble(priceField.getText());
                manager.addItem(new InventoryItem(name, qty, price));
                refreshTable(manager.getItems());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input!");
            }
        });

        updateButton.addActionListener(e -> {
            try {
                String name = nameField.getText();
                int qty = Integer.parseInt(quantityField.getText());
                double price = Double.parseDouble(priceField.getText());
                manager.updateItem(name, new InventoryItem(name, qty, price));
                refreshTable(manager.getItems());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input!");
            }
        });

        deleteButton.addActionListener(e -> {
            String name = nameField.getText();
            manager.deleteItem(name);
            refreshTable(manager.getItems());
        });

        searchButton.addActionListener(e -> {
            String keyword = searchField.getText();
            refreshTable(manager.search(keyword));
        });

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(tablePane, BorderLayout.CENTER);
        frame.add(formPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private void refreshTable(java.util.List<InventoryItem> items) {
        tableModel.setRowCount(0);
        for (InventoryItem i : items) {
            tableModel.addRow(new Object[] { i.getName(), i.getQuantity(), i.getPrice() });
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(InventoryApp::new);
    }
}