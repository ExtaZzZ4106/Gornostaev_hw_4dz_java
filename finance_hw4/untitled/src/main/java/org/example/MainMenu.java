package org.example;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class MainMenu{

    public void finWindow() {
        JFrame window = new JFrame("финансы");
        window.setSize(1000, 500);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setResizable(false);

        JButton redactor = new JButton("Редактировать");
        redactor.setBounds(10, 10, 150, 25);
        window.add(redactor);

        JButton upDate = new JButton("Обновить");
        upDate.setBounds(200, 10, 150, 25);
        window.add(upDate);

        JButton find = new JButton("Найти");
        find.setBounds(400, 10, 150, 25);
        window.add(find);

        JButton delete = new JButton("Удалить");
        delete.setBounds(600, 10, 150, 25);
        window.add(delete);




        Object[][] data = {
                {"Иван", 22},
                {"Мария", 28},
                {"Петр", 35},
                {"Анна", 19}
        };

        String[] columnName1 = {"Имя", "Возраст"};

        Object[][] data1 = {
                {"Иван", 22},
                {"Мария", 28},
                {"Петр", 35},
                {"Анна", 19}
        };

        String[] columnName2 = {"Имя", "Возраст"};

        Object[][] data2 = {
                {"Иван", 22},
                {"Мария", 28},
                {"Петр", 35},
                {"Анна", 19}
        };

        String[] columnName = {"Имя", "Возраст"};

        JTable table1 = new JTable(data, columnName);
        JScrollPane scrollPane = new JScrollPane(table1);
        window.getContentPane().add(scrollPane);
        scrollPane.setSize(300,300);
        scrollPane.setLocation(50,100);

        JTable table2 = new JTable(data1, columnName1);
        JScrollPane scrollPane2 = new JScrollPane(table2);
        window.getContentPane().add(scrollPane2);
        scrollPane2.setSize(300,300);
        scrollPane2.setLocation(350,100);

        JTable table3 = new JTable(data2, columnName2);
        JScrollPane scrollPane3 = new JScrollPane(table3);
        window.getContentPane().add(scrollPane3);
        scrollPane3.setSize(300,300);
        scrollPane3.setLocation(650,100);






        window.add(scrollPane);



    }



}