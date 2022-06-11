package com.senshop.backend.model;

import java.util.ArrayList;

public class Variant {
    private ArrayList<String> size;
    private ArrayList<String> color;
    public Variant() {
    }
    public Variant(ArrayList<String> size, ArrayList<String> color) {
        this.size = size;
        this.color = color;
    }
    public ArrayList<String> getSize() {
        return size;
    }
    public ArrayList<String> getColor() {
        return color;
    }
    public void setSize(ArrayList<String> size) {
        this.size = size;
    }
    public void setColor(ArrayList<String> color) {
        this.color = color;
    }
}
