package com.example.bankapp_aj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private int activedropitem = 0;
    private TextView moneyText;
    private TextView frommoney;
    private TextView tomoney;
    private ArrayAdapter<Account> adapt;
    private ArrayAdapter<AccountActivity> actadapt;

    private TextView nametext;
    private Button accountbutton;
    private Button cardbutton;

    private Spinner dropdown;
    private boolean isadmin;
    private Button transferbutton;
    private Button paymentbutton;
    private Spinner from;
    private Spinner to;
    private ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUicomponents();
        setOnclickListeners();


    }

public void notifyChange() {
        adapt.notifyDataSetChanged();
        actadapt.notifyDataSetChanged();
        moneyText.setText(String.valueOf(Bank.getInstance().getActiveuser().getAccountlist().get(activedropitem).getMoneyamount())+"€");

}

public int getActivedropitem() {
        return this.activedropitem;
}
public void initUicomponents() {
    frommoney = (TextView)findViewById(R.id.frommoney);
    tomoney = (TextView)findViewById(R.id.tomoney);
    moneyText = (TextView)findViewById(R.id.moneyview);
    transferbutton = (Button)findViewById(R.id.transferbutton);
    paymentbutton = (Button)findViewById(R.id.paybutton);
    dropdown = (Spinner) findViewById(R.id.spinner);
    from = (Spinner) findViewById(R.id.fromspinner);
    to = (Spinner) findViewById(R.id.tospinner);
    listview = (ListView)findViewById(R.id.cardlistview);
    nametext = (TextView)findViewById(R.id.nametext);
    accountbutton = (Button)findViewById(R.id.accountbutton);
    cardbutton = (Button)findViewById(R.id.cardbutton);



    adapt = new ArrayAdapter<Account>(this, android.R.layout.simple_dropdown_item_1line, Bank.getInstance().getActiveuser().getAccountlist());
    actadapt = new ArrayAdapter<AccountActivity>(this, R.layout.support_simple_spinner_dropdown_item,
            Bank.getInstance().getActiveuser().getAccountlist().get(activedropitem).getActivityList());
    listview.setAdapter(actadapt);
    dropdown.setAdapter(adapt);
    nametext.setText(Bank.getInstance().getActiveuser().getName());



}
    @Override
    protected void onRestart() {
// TODO Auto-generated method stub
        super.onRestart();
        initUicomponents();
        setOnclickListeners();

    }

public void setOnclickListeners() {
    dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            activedropitem = i;
            moneyText.setText(String.valueOf(Bank.getInstance().getActiveuser().getAccountlist().get(i).getMoneyamount())+"€");
            updateAdapter();

        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    });



    View.OnClickListener listener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {

            if (view == transferbutton){
                openTransferActivity();



            }
            if(view == cardbutton) {
                openCardsActivity();
            }if(view == accountbutton) {
                openAccountsActivity();
            }



        }
    };
    transferbutton.setOnClickListener(listener);
    paymentbutton.setOnClickListener(listener);
    cardbutton.setOnClickListener(listener);
    accountbutton.setOnClickListener(listener);
}


public void updateAdapter() {
        actadapt = new ArrayAdapter<AccountActivity>(this, R.layout.support_simple_spinner_dropdown_item,
                Bank.getInstance().getActiveuser().getAccountlist().get(activedropitem).getActivityList());
        listview.setAdapter(actadapt);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void setChanges(View view) throws IOException {


        String oldname = Bank.getInstance().getActiveuser().getName();
        Bank.getInstance().getActiveuser().setName("ade");
        Bank.getInstance().getActiveuser().setPassword("ade");
        Bank.getInstance().getActiveuser().setAddress("ade");





        DataBaseHandler dataBaseHandler = new DataBaseHandler(this);
        dataBaseHandler.updateUserdata(oldname);
    }

    public void openTransferActivity() {
        Intent intent = new Intent(this, TransferActivity.class);
        startActivity(intent);
    }
    public void openAccountsActivity() {
        Intent intent = new Intent(this, AccountsActivity.class);
        startActivity(intent);
    }
    public void openCardsActivity() {
        Intent intent = new Intent(this, CardsActivity.class);
        startActivity(intent);
    }

}
