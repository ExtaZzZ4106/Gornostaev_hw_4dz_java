package org.example;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    private static final String ROOT_DIRECTORY = System.getProperty("user.dir");

    private static final String NAME = "New file";

    private static JFileChooser f = new JFileChooser();

    int password = 1234;

    public static void main(String[] args) {

        Main main = new Main();
        main.pass();


    }

    public void pass() {
        JFrame frame = new JFrame("Введите пароль");
        JTextField textField = new JTextField();
        JButton button = new JButton("Enter");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textField.setPreferredSize(new Dimension(200, 30));
        frame.setSize(400, 300);
        button.setPreferredSize(new Dimension(200, 30));

        FlowLayout layout = new FlowLayout();

        frame.setLayout(layout);

        frame.add(textField);
        frame.add(button);

        frame.setVisible(true);

        final int pass = this.password;
        button.addActionListener(e -> {
            if (Integer.parseInt(textField.getText()) == pass) {
                notePad();
                frame.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(frame, "Неверный пароль!", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        });


    }

    public static void notePad() {
        JMenuBar menu = new JMenuBar();
        JTabbedPane tabs = new JTabbedPane();

        JMenu file = new JMenu("File");

        JMenuItem newFile = new JMenuItem("New file");
        JMenuItem openFile = new JMenuItem("Open file");
        JMenuItem saveFile = new JMenuItem("Save file");

        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        menu.add(file);



        JFrame window = new JFrame("NotePad");
        window.setSize (800, 600);

        window.setJMenuBar(menu);
        window.add(tabs);




        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);

        newFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextArea text = new JTextArea();
                Scroll scroll = new Scroll(text, false, "");
                tabs.addTab(NAME, scroll);

            }
        });

        saveFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Scroll text = (Scroll) tabs.getSelectedComponent();
                String output = text.getText();
                if (tabs.countComponents() != 0) {
                    if (text.isOpened()) {
                        try {
                            FileOutputStream writer = new FileOutputStream(text.getPath());
                            writer.write(output.getBytes());
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    } else {
                        JFileChooser saveFileChooser = new JFileChooser(new File(ROOT_DIRECTORY, "NotePad\\save"));
                        int result = saveFileChooser.showSaveDialog(null);
                        if (result == JFileChooser.APPROVE_OPTION) {
                            File selectedFile = saveFileChooser.getSelectedFile();
                            try {
                                FileOutputStream writer = new FileOutputStream(selectedFile);
                                writer.write(output.getBytes());
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                }
            }
        });



        openFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.showOpenDialog(null);
                File file = f.getSelectedFile();
                try {
                    // Чтение содержимого файла в массив байтов
                    byte[] bytes = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
                    // Преобразование массива байтов в строку
                    String input = new String(bytes);
                    // Обработка содержимого файла...
                    JTextArea text = new JTextArea(input);
                    Scroll scroll = new Scroll(text, true, file.getAbsolutePath());
                    tabs.addTab(file.getName(), scroll);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
}