package com.example.bankapp_aj;

import androidx.appcompat.app.AppCompatActivity;

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
        accountlistview = (ListView)findViewById(R.id.cardlistview);
        depositamount = (EditText) findViewById(R.id.depositamount);
        addnewdaily = (Button) findViewById(R.id.addnewdaily);
        addnewsaving= (Button) findViewById(R.id.addnewsaving);

        adaptlist = new ArrayAdapter<Account>(this, android.R.layout.simple_dropdown_item_1line, Bank.getInstance().getActiveuser().getAccountlist());
        accountlistview.setAdapter(adaptlist);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (view == addnewdaily) {
                    if(!depositamount.getText().toString().equals("")) {
                        moneyamount = Float.valueOf(depositamount.getText().toString());
                        Bank.getInstance().getActiveuser().addAccountToUser(new DailyAccount(moneyamount));
                        adaptlist.notifyDataSetChanged();
                        DataBaseHandler dataBaseHandler = new DataBaseHandler(AccountsActivity.this);
                        try {
                            dataBaseHandler.updateUserdata(Bank.getInstance().getActiveuser().getName());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    }




                if(view == addnewsaving) {
                    if (!depositamount.getText().toString().equals("")) {
                        moneyamount = Float.valueOf(depositamount.getText().toString());
                        Bank.getInstance().getActiveuser().addAccountToUser(new SavingAccount(moneyamount));
                        adaptlist.notifyDataSetChanged();
                        DataBaseHandler dataBaseHandler = new DataBaseHandler(AccountsActivity.this);
                        try {
                            dataBaseHandler.updateUserdata(Bank.getInstance().getActiveuser().getName());
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
