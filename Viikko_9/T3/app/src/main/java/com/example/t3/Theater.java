package com.example.t3;

/**
 * 7/9/19
 * AtteJantunen
 */
public class Theater {

    private int ID;
    private String name;

    public Theater() {

    }
    public void setName(String name) {
        this.name = name;
    }
    public void setID(int id) {
        this.ID = id;
    }
    public String getName() {
        return this.name;
    }
    public int getId() {
        return this.ID;
    }

    @Override
    public String toString() {
        return getName();
    }
}
