package com.example.bankapp_aj;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.io.IOException;

public class AccountsActivity extends AppCompatActivity {

    private ArrayAdapter<Account> adaptlist;
    private ListView accountlistview;
    private EditText depositamount;
    private Button addnewsaving;
    private Button addnewdaily;
    private float moneyamount=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts);
        accountlistview = (ListView)findViewById(R.id.activitylistview);
        depositamount = (EditText) findViewById(R.id.depositamount);
        addnewdaily = (Button) findViewById(R.id.addnewdaily);
        addnewsaving= (Button) findViewById(R.id.addnewsaving);

        adaptlist = new ArrayAdapter<Account>(this, android.R.layout.simple_dropdown_item_1line, Bank.getInstance().getActiveuser().getAccountlist());
        accountlistview.setAdapter(adaptlist);



        //OnClicklistener for buttons
        View.OnClickListener listener = new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {

                //Add new daily account
                if (view == addnewdaily) {
                    //check for empty
                    if(!depositamount.getText().toString().equals("")) {
                        moneyamount = Float.valueOf(depositamount.getText().toString());
                        Bank.getInstance().getActiveuser().addAccountToUser(new DailyAccount(moneyamount));
                        adaptlist.notifyDataSetChanged();
                        DataBaseHandler dataBaseHandler = new DataBaseHandler(AccountsActivity.this);
                        try {
                            dataBaseHandler.updateUserdata(Bank.getInstance().getActiveuser().getName(),Bank.getInstance().getActiveuser());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    }
                //Add new saving account
                if(view == addnewsaving) {
                    //check for empty
                    if (!depositamount.getText().toString().equals("")) {
                        moneyamount = Float.valueOf(depositamount.getText().toString());
                        Bank.getInstance().getActiveuser().addAccountToUser(new SavingAccount(moneyamount));
                        adaptlist.notifyDataSetChanged();
                        DataBaseHandler dataBaseHandler = new DataBaseHandler(AccountsActivity.this);
                        try {
                            dataBaseHandler.updateUserdata(Bank.getInstance().getActiveuser().getName(),Bank.getInstance().getActiveuser());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }

                }


        };

        addnewdaily.setOnClickListener(listener);
        addnewsaving.setOnClickListener(listener);

    }
}
