package com.example.bankapp_aj;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;

public class Login extends AppCompatActivity {

    private EditText username = null;
    private EditText pass = null;
    private DataBaseHandler dataBaseHandler;
    private Button button;
    private String testname;
    private String testpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.editText2);
        pass = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button4);

        dataBaseHandler = new DataBaseHandler(this);

    }
    @Override protected void onResume() {
        super.onResume();
        Bank.getInstance().setAdminstatus(false);

    }

    public void login(View view) {
        testname = username.getText().toString();
        testpass = pass.getText().toString();


        dataBaseHandler.testquery(testname, testpass);

        if (Bank.getInstance().getActiveuser() != null) {

            if(Bank.getInstance().getActiveuser().getName().equals("admin")){
                Bank.getInstance().setAdminstatus(true);
                dataBaseHandler.filladminlist();
                openMainActivity();
            }else {
                openMainActivity();
            }

        }

    }
    public void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }





}
