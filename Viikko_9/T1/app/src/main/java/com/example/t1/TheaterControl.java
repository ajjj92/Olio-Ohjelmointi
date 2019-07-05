package com.example.t1;

import java.util.ArrayList;

/**
 * 7/5/19
 * AtteJantunen
 */
public class TheaterControl {

    private static TheaterControl theatercontroller=null;
    private ArrayList<Theater> theaterlist;

    public TheaterControl() {

    }

    public static TheaterControl getInstance() {
        if (theatercontroller == null) {
            theatercontroller = new TheaterControl();
        }
        return theatercontroller;
        }


 public void parseTheaterInfo() {

 }
    }

