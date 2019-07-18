package com.example.bankapp_aj;

import android.content.Context;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 7/11/19
 * AtteJantunen
 */
public class Bank {
    private static Bank bank;
    private static ArrayList<User> userlist = new ArrayList<>();
    private String name = "AJ_BANK";
    private User activeuser;

    private Bank() {

    }

    //Singleton Principle
    public static Bank getInstance() {
        if (bank == null) {
            bank = new Bank();

        }
        return bank;
    }

    public void createUser(String name, String password) {
        User user = new User(name, password);
        userlist.add(user);
    }

    public User getActiveuser() {
        return this.activeuser;
    }

    public void setActiveuser(User user ) {
        this.activeuser = user;
    }

    public String getName() {
        return this.name;
    }




    public ArrayList<User> Bank_getUserlist() {
        return userlist;
    }

    public void queryDatabase(DataBaseHandler dataBaseHandler) throws IOException {
        //user = dataBaseHandler.queryUserdata("testimies");

    }
}
