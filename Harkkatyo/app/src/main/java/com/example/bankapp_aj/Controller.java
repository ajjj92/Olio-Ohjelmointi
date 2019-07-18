package com.example.bankapp_aj;

import java.io.IOException;
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
}

