package com.example.t1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    protected  TextView screen;
    protected  BottleDispenser pullomaatti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pullomaatti = BottleDispenser.getInstance();
        screen = (TextView) findViewById(R.id.textView);
        screen.setText(pullomaatti.printBottles());



    }
    public void refreshScreen() {
        screen.setText("");
        screen.setText(pullomaatti.printBottles());

    }

    public void addMoney(View view) {
        pullomaatti.addMoney();

    }

    public void buyBottle(View view) {

        switch (view.getId()) {
            case R.id.pullo1:
                pullomaatti.buyBottle(0);
                break;
            case R.id.pullo2:
                pullomaatti.buyBottle(1);
                break;
            case R.id.pullo3:
                pullomaatti.buyBottle(2);
                break;
            case  R.id.pullo4:
                refreshScreen();

        }
        refreshScreen();

    }
}

