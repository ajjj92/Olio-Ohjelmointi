package com.example.bankapp_aj;

import java.util.ArrayList;

/**
 * 7/11/19
 * AtteJantunen
 */

//Controller acts as a interface for all methods
//This class will make method calls from App Activities more structured.

public class Controller {
    private static Controller controller;
    private Bank bank;
    private Controller() {

    }

//Singleton Principle
public static Controller getInstance() {
    if (controller == null) {
        controller = new Controller();
    }
    return controller;
}

public void Bank_create() {
   bank = Bank.getInstance();
}
public String Bank_getName() {
        return Bank.getInstance().getName();
}
public void Bank_addUser(String name, String password) {
    Bank.getInstance().createUser(name, password);
}
public ArrayList<User> Bank_getUserlist() {
        return Bank.getInstance().getUserlist();
}
}