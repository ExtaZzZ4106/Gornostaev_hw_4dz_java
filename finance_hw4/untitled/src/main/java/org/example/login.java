package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class login {
    public String name;
    public String password_;
    public String tmp_pass;
    public String tmp_name;
    public boolean coneCt = false;
    public boolean on_ofWindow = true;


    public void Sign() {
        this.name = name;
        this.password_ = password_;
        this.coneCt = false;
        this.on_ofWindow = true;
        this.tmp_pass = tmp_pass;
        this.tmp_name = tmp_name;
        main main = new main();
        MainMenu Menu = new MainMenu();




        JFrame window = new JFrame("connect to SQL");
        window.setSize(400, 250);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setVisible(on_ofWindow);
        window.setResizable(false);

        JButton enter = new JButton("enter");
        enter.setBounds(250, 150, 100, 25);
        window.add(enter);

        JTextField name_ = new JTextField();
        name_.setBounds(50, 50, 100, 25);
        window.add(name_);

        JTextField pass = new JTextField();
        pass.setBounds(50, 100, 100, 25);
        window.add(pass);

        JLabel nameL = new JLabel("name");
        nameL.setBounds(150, 50, 100, 25);
        window.add(nameL);

        JLabel passL = new JLabel("password");
        passL.setBounds(150, 100, 100, 25);
        window.add(passL);


        enter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            name = name_.getText();
            password_ = pass.getText();
            tmp_name = name_.getText();
            tmp_pass = pass.getText();
            System.out.println("----------------------\n"
                                +"name: "+name + "\n"
                                +"password: "+ password_);


                String url = "jdbc:mysql://localhost:3306/";
                String username = name;
                String password = password_;


                try (Connection connection = DriverManager.getConnection(url, username, password)) {
                    System.out.println("Connection successful");
                    JOptionPane.showMessageDialog(window, "Соединение успешно");
                    coneCt = true;

                    window.setVisible(false);
                    Menu.finWindow();
                    try (Connection connection_ = DriverManager.getConnection(url, username, password)) {
                        Statement statement = connection_.createStatement();
                        String sql = "create schema if not exists finance";
                        statement.execute(sql);
                        System.out.println("Table created successfully");
                        try (Connection connection_con = DriverManager.getConnection(url, username, password)){
                            Statement statement_tables = connection_con.createStatement();
                            String sql_tables = "USE finance;\n"
                                    +"-- Таблица пользователей\n" +
                                    "CREATE TABLE Users (\n" +
                                    "    UserID INT PRIMARY KEY AUTO_INCREMENT,\n" +
                                    "    Username VARCHAR(50) NOT NULL,\n" +
                                    "    Password VARCHAR(50) NOT NULL,\n" +
                                    "    Email VARCHAR(100) UNIQUE NOT NULL\n" +
                                    ");\n" +
                                    "\n" +
                                    "-- Таблица категорий транзакций\n" +
                                    "CREATE TABLE TransactionCategories (\n" +
                                    "    CategoryID INT PRIMARY KEY AUTO_INCREMENT,\n" +
                                    "    CategoryName VARCHAR(100) NOT NULL\n" +
                                    ");\n" +
                                    "\n" +
                                    "-- Таблица транзакций\n" +
                                    "CREATE TABLE Transactions (\n" +
                                    "    TransactionID INT PRIMARY KEY AUTO_INCREMENT,\n" +
                                    "    UserID INT,\n" +
                                    "    CategoryID INT,\n" +
                                    "    Amount DECIMAL(10, 2) NOT NULL,\n" +
                                    "    TransactionDate DATE NOT NULL,\n" +
                                    "    Description VARCHAR(255),\n" +
                                    "    FOREIGN KEY (UserID) REFERENCES Users(UserID),\n" +
                                    "    FOREIGN KEY (CategoryID) REFERENCES TransactionCategories(CategoryID)\n" +
                                    ");";
                            statement_tables.execute(sql_tables);
                            System.out.println("Tables created successfully");
                        }catch (SQLException a){
                            System.out.println("Error creating tables - org.example.MainMenu.creatBD");

                        }
                    } catch (SQLException a) {
                        System.out.println("Error creating database - org.example.MainMenu.creatBD");
                    }


                } catch (SQLException d) {
                    JOptionPane.showMessageDialog(window, "Не удалось соедениться с баззой данных\n"+
                            "Возможно были введены не верные данные либо не установлен MySQL\n"+
                            "(Проблема может быть ещё в пробеле, если вы по ошибки при вводе даных нажали на пробел\n" +
                            "также высветиться эта ошибка)");
                    System.out.println("connection failed");
                    coneCt = false;
                }
                System.out.println("connetion is: "+coneCt);
            }
        });
    }


}
