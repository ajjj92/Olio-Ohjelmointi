package com.example.t3;

import java.util.ArrayList;

/**
 * 7/9/19
 * AtteJantunen
 */
public class TheaterControl {

    private static TheaterControl theatercontroller = null;
    private ArrayList<Theater> theaterlist = new ArrayList<>();
    private ArrayList<String> movielist=new ArrayList<>();


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
    public ArrayList<String> getMovieLista() {
        return movielist;
    }
    public void addToMovieString(String movie) {
        movielist.add(movie);
    }
    public void ClearMovieList() {
        movielist.clear();
    }
    public void ClearTheaterList() {
        theaterlist.clear();
    }
    public void addToList(Theater Object) {
        theaterlist.add(Object);
    }

    public String createUrl(Theater objekti, String date) {
        if(objekti!=null) {
            String urli = "http://www.finnkino.fi/xml/Schedule/?area="+objekti.getId()+"&dt="+date;
            System.out.println(urli);
            return urli;

        }
        else {
            return null;
        }
    }

}
