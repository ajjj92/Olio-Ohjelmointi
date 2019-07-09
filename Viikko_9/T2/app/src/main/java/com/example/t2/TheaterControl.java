package com.example.t2;


import java.util.ArrayList;


/**
 * 7/5/19
 * AtteJantunen
 */
public class TheaterControl {

    private static TheaterControl theatercontroller = null;
    private ArrayList<Theater> theaterlist = new ArrayList<>();


    private TheaterControl() {

    }

    public static TheaterControl getInstance() {
        if (theatercontroller == null) {
            theatercontroller = new TheaterControl();
        }
        return theatercontroller;
    }

    public ArrayList<Theater> getLista() {
        return theaterlist;
    }

    public void addToList(Theater Object) {
        theaterlist.add(Object);
    }


    }





