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
import android.widget.ListView;
import android.widget.Spinner;

import java.io.IOException;

public class AdminActivity extends AppCompatActivity {

    private Spinner userlist;
    private Spinner accounlist;
    private Spinner cardlist;
    private ArrayAdapter<User> useradapter;
    private ArrayAdapter<Account> accountadapter;
    private ArrayAdapter<Card> cardadapter;
    private ArrayAdapter<AccountActivity> actadapt;

    private EditText editname;
    private EditText editpass;
    private int activeacc=0;
    private int activecard=0;
    private int activeuser=0;
    private int credit=0;
    private EditText creditedit;
    private Button deletecard;
    private Button deleteaccount;
    private Button adduser;
    private ListView activitylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        userlist = (Spinner) findViewById(R.id.userlist);
        accounlist = (Spinner) findViewById(R.id.accountlist);
        cardlist = (Spinner) findViewById(R.id.cardlist);
        editname = (EditText) findViewById(R.id.editname);
        editpass = (EditText) findViewById(R.id.editpass);
        deleteaccount = (Button) findViewById(R.id.buttonaccdel);
        deletecard = (Button) findViewById(R.id.buttoncarddel);
        adduser = (Button) findViewById(R.id.buttonadd);
        activitylist = (ListView)findViewById(R.id.activitylist);
        creditedit = (EditText) findViewById(R.id.editcredit);
        DataBaseHandler dataBaseHandler = new DataBaseHandler(AdminActivity.this);
        dataBaseHandler.filladminlist();
        useradapter = new ArrayAdapter<User>(this, android.R.layout.simple_dropdown_item_1line, Bank.getInstance().getUserlist());
        accountadapter = new ArrayAdapter<Account>(this, android.R.layout.simple_dropdown_item_1line, Bank.getInstance().getUserlist().get(activeuser).getAccountlist());
        cardadapter = new ArrayAdapter<Card>(this, android.R.layout.simple_dropdown_item_1line,Bank.getInstance().getUserlist().get(activeuser).getAccountlist().get(0).getCardlist());

        accounlist.setAdapter(accountadapter);
        userlist.setAdapter(useradapter);
        cardlist.setAdapter(cardadapter);
        actadapt = new ArrayAdapter<AccountActivity>(this, R.layout.support_simple_spinner_dropdown_item,
                Bank.getInstance().getActiveuser().getAccountlist().get(0).getActivityList());
        activitylist.setAdapter(actadapt);


        accounlist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                activeacc= i;

                cardadapter = new ArrayAdapter<Card>(AdminActivity.this, android.R.layout.simple_dropdown_item_1line,Bank.getInstance().getUserlist().get(activeuser).getAccountlist().get(activeacc).getCardlist());
                cardlist.setAdapter(cardadapter);
                actadapt = new ArrayAdapter<AccountActivity>(AdminActivity.this, R.layout.support_simple_spinner_dropdown_item,
                        Bank.getInstance().getUserlist().get(activeuser).getAccountlist().get(activeacc).getActivityList());
                activitylist.setAdapter(actadapt);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        cardlist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                activecard= i;




            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        userlist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                activeuser = i;
                accountadapter = new ArrayAdapter<Account>(AdminActivity.this, android.R.layout.simple_dropdown_item_1line, Bank.getInstance().getUserlist().get(i).getAccountlist());
                accounlist.setAdapter(accountadapter);

                try{
                    actadapt = new ArrayAdapter<AccountActivity>(AdminActivity.this, R.layout.support_simple_spinner_dropdown_item,
                            Bank.getInstance().getUserlist().get(activeuser).getAccountlist().get(activeacc).getActivityList());
                    activitylist.setAdapter(actadapt);
                }catch (IndexOutOfBoundsException e) {
                    e.printStackTrace();
                }



            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        adduser.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                String name = editname.getText().toString();
                String pass = editpass.getText().toString();
                if(name.length() !=0 && pass.length()!=0) {
                    addUser(name, pass);

                }
            }
        });


    }

    public void deleteAccount(View view) throws IOException {
            Bank.getInstance().getUserlist().get(activeuser).getAccountlist().remove(activeacc);
            DataBaseHandler dataBaseHandler = new DataBaseHandler(AdminActivity.this);
            dataBaseHandler.updateUserdata(Bank.getInstance().getUserlist().get(activeuser).getName(), Bank.getInstance().getUserlist().get(activeuser));
            dataBaseHandler.filladminlist();
            accountadapter = new ArrayAdapter<Account>(this, android.R.layout.simple_dropdown_item_1line, Bank.getInstance().getUserlist().get(activeuser).getAccountlist());
            accounlist.setAdapter(accountadapter);


    }
    public void deleteCard(View view) {
        try{
            Bank.getInstance().getUserlist().get(activeuser).getAccountlist().get(activeacc).getCardlist().remove(activecard);
            DataBaseHandler dataBaseHandler = new DataBaseHandler(AdminActivity.this);
            dataBaseHandler.updateUserdata(Bank.getInstance().getUserlist().get(activeuser).getName(), Bank.getInstance().getUserlist().get(activeuser));
            dataBaseHandler.filladminlist();
            cardadapter = new ArrayAdapter<Card>(this, android.R.layout.simple_dropdown_item_1line,Bank.getInstance().getUserlist().get(activeuser).getAccountlist().get(activeacc).getCardlist());
            cardlist.setAdapter(cardadapter);
        } catch (IllegalStateException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void deleteUser(View view) {
        DataBaseHandler dataBaseHandler = new DataBaseHandler(AdminActivity.this);
        dataBaseHandler.deleteUserData(Bank.getInstance().getUserlist().get(activeuser).getName());
        dataBaseHandler.filladminlist();
        useradapter = new ArrayAdapter<User>(this, android.R.layout.simple_dropdown_item_1line, Bank.getInstance().getUserlist());
        userlist.setAdapter(useradapter);

    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addUser(String name, String pass) {
        DataBaseHandler dataBaseHandler = new DataBaseHandler(AdminActivity.this);
        User user = new User (name, pass);
        Bank.getInstance().setTempuser(user);
        Bank.getInstance().getTempuser().addAccountToUser(new DailyAccount(0));
        dataBaseHandler.addData(user);
        dataBaseHandler.filladminlist();
        useradapter.notifyDataSetChanged();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addDailyAccount(View view) throws IOException {
        Bank.getInstance().getUserlist().get(activeuser).addAccountToUser(new DailyAccount(0));
        DataBaseHandler dataBaseHandler = new DataBaseHandler(AdminActivity.this);
        dataBaseHandler.updateUserdata(Bank.getInstance().getUserlist().get(activeuser).getName(), Bank.getInstance().getUserlist().get(activeuser));
        accountadapter = new ArrayAdapter<Account>(this, android.R.layout.simple_dropdown_item_1line, Bank.getInstance().getUserlist().get(activeuser).getAccountlist());
        accounlist.setAdapter(accountadapter);

    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addSavingAccount(View view) throws IOException {
        Bank.getInstance().getUserlist().get(activeuser).addAccountToUser(new SavingAccount(0));
        DataBaseHandler dataBaseHandler = new DataBaseHandler(AdminActivity.this);
        dataBaseHandler.updateUserdata(Bank.getInstance().getUserlist().get(activeuser).getName(), Bank.getInstance().getUserlist().get(activeuser));
        accountadapter = new ArrayAdapter<Account>(this, android.R.layout.simple_dropdown_item_1line, Bank.getInstance().getUserlist().get(activeuser).getAccountlist());
        accounlist.setAdapter(accountadapter);

    }
    public void addDebitCard(View view) {
        Bank.getInstance().getUserlist().get(activeuser).getAccountlist().get(activeacc).addCardtoAccount(
                new DebitCard(Bank.getInstance().getUserlist().get(activeuser).getAccountlist().get(activeacc)));
        cardadapter = new ArrayAdapter<Card>(this, android.R.layout.simple_dropdown_item_1line,Bank.getInstance().getUserlist().get(activeuser).
                getAccountlist().get(activeacc).getCardlist());
        cardlist.setAdapter(cardadapter);
    }

    public void addCreditCard(View view) throws IOException {
        credit = Integer.valueOf(creditedit.getText().toString());
        Bank.getInstance().getUserlist().get(activeuser).getAccountlist().get(activeacc).addCardtoAccount(
                new CreditCard(Bank.getInstance().getUserlist().get(activeuser).getAccountlist().get(activeacc),
                        credit));
        DataBaseHandler dataBaseHandler = new DataBaseHandler(AdminActivity.this);
        dataBaseHandler.updateUserdata(Bank.getInstance().getUserlist().get(activeuser).getName(), Bank.getInstance().getUserlist().get(activeuser));
        dataBaseHandler.filladminlist();
        cardadapter = new ArrayAdapter<Card>(this, android.R.layout.simple_dropdown_item_1line,Bank.getInstance().getUserlist().get(activeuser).getAccountlist().get(activeacc).getCardlist());
        cardlist.setAdapter(cardadapter);

    }
}

