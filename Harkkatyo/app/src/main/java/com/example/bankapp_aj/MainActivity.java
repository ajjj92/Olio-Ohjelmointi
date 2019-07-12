package com.example.bankapp_aj;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private Controller controller;
    private int i = 0;
    private TextView accounttext;
    DataBaseHandler dataBaseHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        controller = Controller.getInstance();
        controller.Bank_create();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataBaseHandler = new DataBaseHandler(this);
        accounttext = (TextView) findViewById(R.id.accountview);

    }

    public void printname(View view) throws IOException {

    }

    public void testiadd(View view) {
        controller.Bank_addUser("Atte", "passu");
        boolean insertdata = dataBaseHandler.addData(controller.Bank_getUserlist().get(i));
        i++;
    }
    public void testUserList(View view) {
        System.out.println(controller.Bank_getUserlist());
    }
}
