package com.example.bankapp_aj;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    private Bank controller;
    private int activedropitem = 0;
    private TextView moneyText;
    private TextView frommoney;
    private TextView tomoney;
    private ArrayAdapter<Account> adapt;
    private ArrayAdapter<AccountActivity> actadapt;
    ArrayList<AccountActivity> reverselist;
    private Fragment transferfragmnet;
    private Fragment paymentfragment;
    private Fragment activityfragment;

    private DataBaseHandler dataBaseHandler;
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
        initFragments();
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
    listview = (ListView)findViewById(R.id.listview);
    reverselist = Bank.getInstance().getActiveuser().getAccountlist().get(activedropitem).getActivityList();
    Collections.reverse(reverselist);

    adapt = new ArrayAdapter<Account>(this, android.R.layout.simple_dropdown_item_1line, Bank.getInstance().getActiveuser().getAccountlist());
    actadapt = new ArrayAdapter<AccountActivity>(this, R.layout.support_simple_spinner_dropdown_item,
            reverselist);
    listview.setAdapter(actadapt);
    dropdown.setAdapter(adapt);



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
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.fragmentwindow, transferfragmnet);
                transaction.commit();



            }
            if(view == paymentbutton) {
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.fragmentwindow, paymentfragment);
                transaction.commit();

            }



        }
    };
    transferbutton.setOnClickListener(listener);
    paymentbutton.setOnClickListener(listener);
}

public void initFragments() {
    transferfragmnet =new fragment_account();
    paymentfragment = new fragment_payment();



}
public void updateAdapter() {
        reverselist = Bank.getInstance().getActiveuser().getAccountlist().get(activedropitem).getActivityList();
        Collections.reverse(reverselist);
        actadapt = new ArrayAdapter<AccountActivity>(this, R.layout.support_simple_spinner_dropdown_item,
                reverselist);
        listview.setAdapter(actadapt);
    }

}
