package com.example.bankapp_aj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
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
        dataBaseHandler = new DataBaseHandler(this);
        button = (Button) findViewById(R.id.button4);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                test();
            }
        });

    }


    public void login(View view) {
        testname = username.getText().toString();
        testpass = pass.getText().toString();


        dataBaseHandler.testquery(testname, testpass);

        if (Bank.getInstance().getActiveuser() != null) {

            openMainActivity();


        }

    }
    public void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void test() {
        User user = new User("admin", "admin");
        Bank.getInstance().setActiveuser(user);
        Bank.getInstance().getActiveuser().addAccountToUser(new DailyAccount(100));
        dataBaseHandler.addData(user);
        dataBaseHandler.close();

    }
    public void deletedata(View view) {
        dataBaseHandler.deletedata();
    }


}
