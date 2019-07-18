package com.example.bankapp_aj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;

public class Login extends AppCompatActivity {

    private EditText username = null;
    private EditText pass = null;
    private DataBaseHandler dataBaseHandler;
    private User user = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.editText2);
        pass = (EditText) findViewById(R.id.editText);
        dataBaseHandler = new DataBaseHandler(this);

    }


    public void login(View view) throws IOException {
        user = dataBaseHandler.queryUserdata(username.getText().toString(), pass.getText().toString());
        Bank.getInstance().setActiveuser(user);
        if(user != null) {

            openMainActivity(user);

        } else {
            //wrong password
        }

    }

    public void openMainActivity(User user) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void test(View view) {
        User user = new User("admin", "admin");
        Bank.getInstance().setActiveuser(user);
        Bank.getInstance().getActiveuser().addAccountToUser(new DailyAccount());
        Bank.getInstance().getActiveuser().addAccountToUser(new SavingAccount());
        dataBaseHandler.addData(user);
    }


}
