package com.example.bankapp_aj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class Login extends AppCompatActivity {

    private EditText username = null;
    private EditText pass = null;
    private DataBaseHandler dataBaseHandler;
    private String name;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.editText2);
        pass = (EditText) findViewById(R.id.editText);

        dataBaseHandler = new DataBaseHandler(this);

    }
    //Reset admin status when changing users
    @Override protected void onResume() {
        super.onResume();
        Bank.getInstance().setAdminstatus(false);

    }

    public void login(View view) {
        name = username.getText().toString();
        password = pass.getText().toString();


        dataBaseHandler.mainQuery(name, password);
        //Check if user was found
        if (Bank.getInstance().getActiveuser() != null) {
            //admin check
            if(Bank.getInstance().getActiveuser().getName().equals("admin")){
                Bank.getInstance().setAdminstatus(true);
                dataBaseHandler.fillAdminlist();
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
