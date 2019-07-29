package com.example.bankapp_aj;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import java.io.IOException;
import java.security.SecurityPermission;

public class PaymentActivity extends AppCompatActivity {

    private boolean isAjBank = false;
    private Switch ajbankswitch;
    private Spinner tempuserlist;
    private Spinner activeuserlist;
    private ArrayAdapter<Account> tempuseradapter;
    private ArrayAdapter<Account> activeuseradapter;
    private EditText name;
    private String namestring;
    private Button applybutton;
    private Button accept;
    private EditText moneytosend;
    private int selectedtempaccount;
    private int selectedactiveaccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        ajbankswitch = findViewById(R.id.is_ajbank);
        applybutton = findViewById(R.id.applybutton);
        name = findViewById(R.id.editTextname);
        tempuserlist = findViewById(R.id.spinnerusers);
        activeuserlist  = findViewById(R.id.spinner2);
        activeuseradapter = new ArrayAdapter<Account>(PaymentActivity.this, R.layout.support_simple_spinner_dropdown_item,
                Bank.getInstance().getActiveuser().getAccountlist());
        activeuserlist.setAdapter(activeuseradapter);
        moneytosend = findViewById(R.id.amounttosend);

        ajbankswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == true) {
                    isAjBank = true;



                } else if (b == false) {
                    isAjBank = false;

                }
            }
        });

        tempuserlist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    selectedtempaccount = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        activeuserlist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedactiveaccount = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }



    public void newPayment(View view) {

        if(isAjBank) {
            namestring = name.getText().toString();


            DataBaseHandler dataBaseHandler = new DataBaseHandler(this);
            dataBaseHandler.queryNopass(namestring);
            if (Bank.getInstance().getTempuser() != null) {
                tempuseradapter = new ArrayAdapter<Account>(PaymentActivity.this, R.layout.support_simple_spinner_dropdown_item,
                        Bank.getInstance().getTempuser().getAccountlist());
                tempuserlist.setAdapter(tempuseradapter);
            }

        } else {

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void sendMoney(View view) throws IOException {
            Float moneyamount = Float.valueOf(moneytosend.getText().toString());
        if(moneyamount <= Bank.getInstance().getActiveuser().getAccountlist().get(selectedactiveaccount).getMoneyamount()) {
            Bank.getInstance().getActiveuser().getAccountlist().get(selectedactiveaccount).takeMoney(moneyamount,
                    Bank.getInstance().getActiveuser().getName(), Bank.getInstance().getTempuser().getName());

            Bank.getInstance().getTempuser().getAccountlist().get(selectedtempaccount).addMoney(moneyamount,
                    Bank.getInstance().getActiveuser().getName(), Bank.getInstance().getTempuser().getName());

            DataBaseHandler dataBaseHandler = new DataBaseHandler(this);
            dataBaseHandler.updateUserdata(Bank.getInstance().getActiveuser().getName(),Bank.getInstance().getActiveuser());

            dataBaseHandler.updateUserdata(Bank.getInstance().getTempuser().getName(),Bank.getInstance().getTempuser());
        }


    }
}
