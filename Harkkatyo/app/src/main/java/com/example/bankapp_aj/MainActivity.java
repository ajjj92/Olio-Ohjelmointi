package com.example.bankapp_aj;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private Controller controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        controller = Controller.getInstance();
        controller.Bank_create();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void printname(View view) {
        System.out.println(controller.Bank_getName());
    }

    public void testiadd(View view) {
        controller.Bank_addUser("testi", "passu");
    }
    public void testUserList(View view) {
        System.out.println(controller.Bank_getUserlist());
    }
}
