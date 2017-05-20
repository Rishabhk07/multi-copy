package com.condingblocks.multicopy.model;

import java.util.ArrayList;

/**
 * Created by rishabhkhanna on 12/05/17.
 */

public class ClipboardTextModel {
    String text;
    ArrayList<String> textArrayList;

    public ClipboardTextModel(String text, ArrayList<String> textArrayList) {
        this.text = text;
        this.textArrayList = textArrayList;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ArrayList<String> getTextArrayList() {
        return textArrayList;
    }

    public void setTextArrayList(ArrayList<String> textArrayList) {
        this.textArrayList = textArrayList;
    }
}
