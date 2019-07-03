package com.example.t5;

/**
 * 7/3/19
 * AtteJantunen
 */
public class Bottle {

    private String name=null;
    private Float size;
    private Float price;

    public Bottle(String name, Float size, Float price) {

        this.name = name;
        this.size = size;
        this.price = price;

    }

    public String getName() {
        return this.name;
    }

    public Float getSize() {
        return size;
    }

    public Float getPrice()  {
        return price;
    }

    @Override
    public String toString() {
        return getName()+" "+getSize()+" l";
    }
}

