package com.example.bankapp_aj;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.IOException;

public class CardsActivity extends AppCompatActivity {

    private ArrayAdapter<Card> cardadapt;
    private ArrayAdapter<Account> accountadapt;
    private Spinner cardlistview;
    private EditText creditlimit;
    private Button addnewdebitcard;
    private Button addnewcreditcard;
    private Button deposit;
    private Button withdraw;
    private int activedropitem;
    private int activecarddopitem;
    private Spinner accountlist;
    private EditText moneyamount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards);

        accountlist = (Spinner) findViewById(R.id.linkedaccount);
        cardlistview = (Spinner) findViewById(R.id.activitylistview);
        creditlimit = (EditText) findViewById(R.id.creditlimit);
        addnewcreditcard = (Button) findViewById(R.id.creditbutton);
        addnewdebitcard = (Button) findViewById(R.id.debitbutton);
        deposit = (Button) findViewById(R.id.depbutton);
        withdraw = (Button) findViewById(R.id.withbutton);
        moneyamount = (EditText) findViewById(R.id.withmoneyamount);
        accountadapt = new ArrayAdapter<Account>(this, android.R.layout.simple_dropdown_item_1line, Bank.getInstance().getActiveuser().getAccountlist());
        creditlimit.setText("0");
        cardadapt = new ArrayAdapter<Card>(this, android.R.layout.simple_dropdown_item_1line, Bank.getInstance().getActiveuser().getAccountlist().get(activedropitem).getCardlist());
        cardlistview.setAdapter(cardadapt);
        accountlist.setAdapter(accountadapt);




        View.OnClickListener listener3 = new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                int creditlimittosend;
                if (view == addnewcreditcard){
                    creditlimittosend = Integer.valueOf(creditlimit.getText().toString());
                    Bank.getInstance().getActiveuser().getAccountlist().get(activedropitem).addCardtoAccount(new CreditCard(Bank.getInstance().getActiveuser().getAccountlist().get(activedropitem), creditlimittosend));
                    DataBaseHandler dataBaseHandler = new DataBaseHandler(CardsActivity.this);
                    try {
                        dataBaseHandler.updateUserdata(Bank.getInstance().getActiveuser().getName(),Bank.getInstance().getActiveuser());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    cardadapt.notifyDataSetChanged();

                }
                if(view == addnewdebitcard) {
                    Bank.getInstance().getActiveuser().getAccountlist().get(activedropitem).addCardtoAccount(new DebitCard(Bank.getInstance().getActiveuser().getAccountlist().get(activedropitem)));
                    DataBaseHandler dataBaseHandler = new DataBaseHandler(CardsActivity.this);
                    try {
                        dataBaseHandler.updateUserdata(Bank.getInstance().getActiveuser().getName(),Bank.getInstance().getActiveuser());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    cardadapt.notifyDataSetChanged();



                } if(view == withdraw) {
                    try {
                        Withdraw(Float.valueOf(moneyamount.getText().toString()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } if (view == deposit) {
                    try {
                        Deposit(Float.valueOf(moneyamount.getText().toString()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        };

        addnewcreditcard.setOnClickListener(listener3);
        addnewdebitcard.setOnClickListener(listener3);
        withdraw.setOnClickListener(listener3);
        deposit.setOnClickListener(listener3);

        accountlist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                activedropitem = i;
                cardadapt = new ArrayAdapter<Card>(CardsActivity.this, android.R.layout.simple_dropdown_item_1line,Bank.getInstance().getActiveuser().getAccountlist().get(activedropitem).getCardlist());
                cardlistview.setAdapter(cardadapt);



            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        cardlistview.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                activecarddopitem= i;


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void updateAdapter() {
        cardadapt = new ArrayAdapter<Card>(this, R.layout.support_simple_spinner_dropdown_item,
                Bank.getInstance().getActiveuser().getAccountlist().get(activedropitem).getCardlist());
        cardlistview.setAdapter(cardadapt);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void Withdraw(float moneyamount) throws IOException {

        if(Bank.getInstance().getActiveuser().getAccountlist().get(activedropitem).getCardlist().
                get(activecarddopitem).getCardtype() == "Creditcard") {
            if(moneyamount <= Bank.getInstance().getActiveuser().getAccountlist().get(activedropitem).
                    getCardlist().get(activecarddopitem).getCreditlimit() +
                    Bank.getInstance().getActiveuser().getAccountlist().get(activedropitem).getCardlist().
                            get(activecarddopitem).getLinkedaccount().getMoneyamount()) {
                Bank.getInstance().getActiveuser().getAccountlist().get(activedropitem).getCardlist().
                        get(activecarddopitem).getLinkedaccount().takeMoney(moneyamount);
                DataBaseHandler dataBaseHandler = new DataBaseHandler(CardsActivity.this);
                dataBaseHandler.updateUserdata(Bank.getInstance().getActiveuser().getName(),Bank.getInstance().getActiveuser());
            }
        }


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void Deposit(float moneyamount) throws IOException {
        Bank.getInstance().getActiveuser().getAccountlist().get(activedropitem).getCardlist().get(activecarddopitem).getLinkedaccount().addMoney(moneyamount);
        DataBaseHandler dataBaseHandler = new DataBaseHandler(CardsActivity.this);
        dataBaseHandler.updateUserdata(Bank.getInstance().getActiveuser().getName(),Bank.getInstance().getActiveuser());


    }

}
