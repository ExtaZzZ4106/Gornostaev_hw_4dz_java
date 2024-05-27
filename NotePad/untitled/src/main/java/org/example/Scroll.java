package org.example;

import javax.swing.*;

public class Scroll extends JScrollPane {

    private final String path;

    private final boolean isOpened;



    private final JTextArea text;

    public Scroll(JTextArea text, boolean isOpened, String path) {
        super(text);
        this.text = text;
        this.path = path;
        this.isOpened = isOpened;
    }

    public String getText() {
        return text.getText();
    }

    public boolean isOpened() {
        return isOpened;
    }

    public String getPath() {
        return path;
    }




}
