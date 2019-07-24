package com.example.bankapp_aj;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

public class CardsActivity extends AppCompatActivity {

    private ArrayAdapter<Card> cardadapt;
    private ArrayAdapter<Account> accountadapt;
    private ListView cardlistview;
    private EditText creditlimit;
    private Button addnewdebitcard;
    private Button addnewcreditcard;
    private int activedropitem;
    private Spinner accountlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards);

        accountlist = (Spinner) findViewById(R.id.linkedaccount);
        cardlistview = (ListView) findViewById(R.id.cardlistview);
        creditlimit = (EditText) findViewById(R.id.creditlimit);
        addnewcreditcard = (Button) findViewById(R.id.creditbutton);
        addnewdebitcard = (Button) findViewById(R.id.debitbutton);
        accountadapt = new ArrayAdapter<Account>(this, android.R.layout.simple_dropdown_item_1line, Bank.getInstance().getActiveuser().getAccountlist());
        creditlimit.setText("0");
        cardadapt = new ArrayAdapter<Card>(this, android.R.layout.simple_dropdown_item_1line, Bank.getInstance().getActiveuser().getAccountlist().get(activedropitem).getCardlist());
        cardlistview.setAdapter(cardadapt);
        accountlist.setAdapter(accountadapt);




        View.OnClickListener listener3 = new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int creditlimittosend;
                if (view == addnewcreditcard){
                    creditlimittosend = Integer.valueOf(creditlimit.getText().toString());
                    Bank.getInstance().getActiveuser().getAccountlist().get(activedropitem).addCardtoAccount(new CreditCard(Bank.getInstance().getActiveuser().getAccountlist().get(activedropitem), creditlimittosend));
                    cardadapt.notifyDataSetChanged();

                }
                if(view == addnewdebitcard) {
                    Bank.getInstance().getActiveuser().getAccountlist().get(activedropitem).addCardtoAccount(new DebitCard(Bank.getInstance().getActiveuser().getAccountlist().get(activedropitem)));
                    cardadapt.notifyDataSetChanged();



                }

            }
        };

        addnewcreditcard.setOnClickListener(listener3);
        addnewdebitcard.setOnClickListener(listener3);

        accountlist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                activedropitem = i;

                updateAdapter();


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

}
