package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Transaction Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 450);

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton addButton = new JButton("Add");
        JButton deleteButton = new JButton("Delete");

        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);

        mainPanel.add(buttonPanel, BorderLayout.NORTH);

        JPanel tablePanel = new JPanel(new GridLayout(1, 3));

        DefaultTableModel userModel = new DefaultTableModel();
        JTable userTable = new JTable(userModel);
        JScrollPane userScrollPane = new JScrollPane(userTable);
        userScrollPane.setPreferredSize(new Dimension(400, 300));
        tablePanel.add(userScrollPane);

        userModel.addColumn("UserID");
        userModel.addColumn("Username");
        userModel.addColumn("Password");
        userModel.addColumn("Email");

        DefaultTableModel categoryModel = new DefaultTableModel();
        JTable categoryTable = new JTable(categoryModel);
        JScrollPane categoryScrollPane = new JScrollPane(categoryTable);
        categoryScrollPane.setPreferredSize(new Dimension(400, 300));
        tablePanel.add(categoryScrollPane);

        categoryModel.addColumn("CategoryID");
        categoryModel.addColumn("CategoryName");

        DefaultTableModel transactionModel = new DefaultTableModel();
        JTable transactionTable = new JTable(transactionModel);
        JScrollPane transactionScrollPane = new JScrollPane(transactionTable);
        transactionScrollPane.setPreferredSize(new Dimension(400, 300));
        tablePanel.add(transactionScrollPane);

        transactionModel.addColumn("TransactionID");
        transactionModel.addColumn("UserID");
        transactionModel.addColumn("CategoryID");
        transactionModel.addColumn("Amount");
        transactionModel.addColumn("TransactionDate");
        transactionModel.addColumn("Description");

        addButton.addActionListener(e -> {
            JDialog addDialog = new JDialog(frame, "Add New Entry", true);
            JTabbedPane tabbedPane = new JTabbedPane();

            JPanel userPanel = createUserPanel(userModel);
            JPanel categoryPanel = createCategoryPanel(categoryModel);
            JPanel transactionPanel = createTransactionPanel(transactionModel);

            tabbedPane.addTab("Users", userPanel);
            tabbedPane.addTab("Categories", categoryPanel);
            tabbedPane.addTab("Transactions", transactionPanel);

            addDialog.add(tabbedPane);
            addDialog.setSize(500, 300);
            addDialog.setLocationRelativeTo(frame);
            addDialog.setVisible(true);
        });

        deleteButton.addActionListener(e -> {
            int ut = userTable.getSelectedRow();
            int ct = categoryTable.getSelectedRow();
            int t = transactionTable.getSelectedRow();

            if (ut != -1) {
                int userId = (int) userTable.getValueAt(ut, 0);
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/finance", "root", "123456789");
                    PreparedStatement statement = connection.prepareStatement("DELETE FROM Users WHERE UserID = ?");
                    statement.setInt(1, userId);
                    statement.executeUpdate();
                    userModel.setRowCount(0);
                    ResultSet userResultSet = statement.executeQuery("SELECT * FROM Users");
                    while (userResultSet.next()) {
                        Object[] row = {
                                userResultSet.getInt("UserID"),
                                userResultSet.getString("Username"),
                                userResultSet.getString("Password"),
                                userResultSet.getString("Email")
                        };
                        userModel.addRow(row);
                    }
                    userResultSet.close();
                    statement.close();
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            } else if (ct != -1) {
                int categoryId = (int) categoryTable.getValueAt(ct, 0);
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/finance", "root", "123456789");
                    PreparedStatement statement = connection.prepareStatement("DELETE FROM TransactionCategories WHERE CategoryID = ?");
                    statement.setInt(1, categoryId);
                    statement.executeUpdate();
                    categoryModel.setRowCount(0);
                    ResultSet categoryResultSet = statement.executeQuery("SELECT * FROM TransactionCategories");
                    while (categoryResultSet.next()) {
                        Object[] row = {
                                categoryResultSet.getInt("CategoryID"),
                                categoryResultSet.getString("CategoryName")
                        };
                        categoryModel.addRow(row);
                    }
                    categoryResultSet.close();
                    statement.close();
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            } else if (t != -1) {
                int transactionId = (int) transactionTable.getValueAt(t, 0);
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/finance", "root", "123456789");
                    PreparedStatement statement = connection.prepareStatement("DELETE FROM Transactions WHERE TransactionID = ?");
                    statement.setInt(1, transactionId);
                    statement.executeUpdate();
                    transactionModel.setRowCount(0);
                    ResultSet transactionResultSet = statement.executeQuery("SELECT * FROM Transactions");
                    while (transactionResultSet.next()) {
                        Object[] row = {
                                transactionResultSet.getInt("TransactionID"),
                                transactionResultSet.getInt("UserID"),
                                transactionResultSet.getInt("CategoryID"),
                                transactionResultSet.getString("Amount"),
                                transactionResultSet.getString("TransactionDate"),
                                transactionResultSet.getString("Description")
                        };
                        transactionModel.addRow(row);
                    }
                    transactionResultSet.close();
                    statement.close();
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please select a row to delete.");
            }
        });

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/finance", "root", "123456789");
            Statement statement = connection.createStatement();
            ResultSet userResultSet = statement.executeQuery("SELECT * FROM Users");
            while (userResultSet.next()) {
                Object[] row = {
                        userResultSet.getInt("UserID"),
                        userResultSet.getString("Username"),
                        userResultSet.getString("Password"),
                        userResultSet.getString("Email")
                };
                userModel.addRow(row);
            }
            userResultSet.close();

            ResultSet categoryResultSet = statement.executeQuery("SELECT * FROM TransactionCategories");
            while (categoryResultSet.next()) {
                Object[] row = {
                        categoryResultSet.getInt("CategoryID"),
                        categoryResultSet.getString("CategoryName")
                };
                categoryModel.addRow(row);
            }
            categoryResultSet.close();

            ResultSet transactionResultSet = statement.executeQuery("SELECT * FROM Transactions");
            while (transactionResultSet.next()) {
                Object[] row = {
                        transactionResultSet.getInt("TransactionID"),
                        transactionResultSet.getInt("UserID"),
                        transactionResultSet.getInt("CategoryID"),
                        transactionResultSet.getString("Amount"),
                        transactionResultSet.getString("TransactionDate"),
                        transactionResultSet.getString("Description")
                };
                transactionModel.addRow(row);
            }
            transactionResultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        mainPanel.add(tablePanel, BorderLayout.CENTER);
        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private static JPanel createUserPanel(DefaultTableModel model) {
        JPanel panel = new JPanel(new GridLayout(5, 2));

        JTextField userIDField = new JTextField();
        JTextField usernameField = new JTextField();
        JTextField passwordField = new JTextField();
        JTextField emailField = new JTextField();

        panel.add(new JLabel("UserID:"));
        panel.add(userIDField);
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);

        JButton addButton = new JButton("Enter");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int userID = Integer.parseInt(userIDField.getText());
                String username = usernameField.getText();
                String password = passwordField.getText();
                String email = emailField.getText();

                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/finance", "root", "123456789");
                    PreparedStatement statement = connection.prepareStatement("INSERT INTO Users (UserID, Username, Password, Email) VALUES (?, ?, ?, ?)");
                    statement.setInt(1, userID);
                    statement.setString(2, username);
                    statement.setString(3, password);
                    statement.setString(4, email);
                    statement.executeUpdate();
                    connection.close();
                    statement.close();
                    model.addRow(new Object[]{userID, username, password, email});
                    userIDField.setText("");
                    usernameField.setText("");
                    passwordField.setText("");
                    emailField.setText("");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        panel.add(addButton);
        return panel;
    }

    private static JPanel createCategoryPanel(DefaultTableModel model) {
        JPanel panel = new JPanel(new GridLayout(2, 2));

        JTextField categoryIDField = new JTextField();
        JTextField categoryNameField = new JTextField();

        panel.add(new JLabel("CategoryID:"));
        panel.add(categoryIDField);
        panel.add(new JLabel("CategoryName:"));
        panel.add(categoryNameField);

        JButton addButton = new JButton("Enter");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int categoryID = Integer.parseInt(categoryIDField.getText());
                String categoryName = categoryNameField.getText();

                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/finance", "root", "123456789");
                    PreparedStatement statement = connection.prepareStatement("INSERT INTO TransactionCategories (CategoryID, CategoryName) VALUES (?, ?)");
                    statement.setInt(1, categoryID);
                    statement.setString(2, categoryName);
                    statement.executeUpdate();
                    connection.close();
                    statement.close();
                    model.addRow(new Object[]{categoryID, categoryName});
                    categoryIDField.setText("");
                    categoryNameField.setText("");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        panel.add(addButton);
        return panel;
    }

    private static JPanel createTransactionPanel(DefaultTableModel model) {
        JPanel panel = new JPanel(new GridLayout(6, 2));

        JTextField transactionIDField = new JTextField();
        JTextField userIDField = new JTextField();
        JTextField categoryIDField = new JTextField();
        JTextField amountField = new JTextField();
        JTextField dateField = new JTextField();
        JTextField descriptionField = new JTextField();

        panel.add(new JLabel("TransactionID:"));
        panel.add(transactionIDField);
        panel.add(new JLabel("UserID:"));
        panel.add(userIDField);
        panel.add(new JLabel("CategoryID:"));
        panel.add(categoryIDField);
        panel.add(new JLabel("Amount:"));
        panel.add(amountField);
        panel.add(new JLabel("TransactionDate:"));
        panel.add(dateField);
        panel.add(new JLabel("Description:"));
        panel.add(descriptionField);

        JButton addButton = new JButton("Enter");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int transactionID = Integer.parseInt(transactionIDField.getText());
                int userID = Integer.parseInt(userIDField.getText());
                int categoryID = Integer.parseInt(categoryIDField.getText());
                String amount = amountField.getText();
                String date = dateField.getText();
                String description = descriptionField.getText();

                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/finance", "root", "123456789");
                    PreparedStatement statement = connection.prepareStatement("INSERT INTO Transactions (TransactionID, UserID, CategoryID, Amount, TransactionDate, Description) VALUES (?, ?, ?, ?, ?, ?)");
                    statement.setInt(1, transactionID);
                    statement.setInt(2, userID);
                    statement.setInt(3, categoryID);
                    statement.setString(4, amount);
                    statement.setString(5, date);
                    statement.setString(6, description);
                    statement.executeUpdate();
                    connection.close();
                    statement.close();
                    model.addRow(new Object[]{transactionID, userID, categoryID, amount, date, description});
                    transactionIDField.setText("");
                    userIDField.setText("");
                    categoryIDField.setText("");
                    amountField.setText("");
                    dateField.setText("");
                    descriptionField.setText("");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        panel.add(addButton);
        return panel;
    }
}
