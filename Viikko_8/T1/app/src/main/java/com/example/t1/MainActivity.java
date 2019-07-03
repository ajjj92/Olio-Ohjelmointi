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
    private TextView printscreen;
    private Button changeout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pullomaatti = BottleDispenser.getInstance();
        screen = (TextView) findViewById(R.id.textView);
        screen.setText(pullomaatti.printBottles());
        printscreen = (TextView) findViewById(R.id.textView2);
        changeout = (Button) findViewById(R.id.button2);



    }
    public void refreshScreen() {
        screen.setText("");
        screen.setText(pullomaatti.printBottles());

    }


    public void addMoney(View view) {
        printscreen.setText(pullomaatti.addMoney());

    }

    public void changeout(View view) {
        printscreen.setText(pullomaatti.returnMoney());
    }

    public void buyBottle(View view) {

        switch (view.getId()) {
            case R.id.pullo1:
                printscreen.setText(pullomaatti.buyBottle(0));
                break;
            case R.id.pullo2:
                printscreen.setText(pullomaatti.buyBottle(1));
                break;
            case R.id.pullo3:
                printscreen.setText(pullomaatti.buyBottle(2));
                break;
            case  R.id.pullo4:
                printscreen.setText(pullomaatti.buyBottle(3));
                break;
            case R.id.pullo5:
                printscreen.setText(pullomaatti.buyBottle(4));
                break;
            case R.id.pullo6:
                printscreen.setText(pullomaatti.buyBottle(5));
                break;
        }
        refreshScreen();

    }
}

