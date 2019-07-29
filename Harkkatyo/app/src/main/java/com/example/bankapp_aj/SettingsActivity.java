package com.example.bankapp_aj;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import java.io.IOException;

public class SettingsActivity extends AppCompatActivity {

    private EditText name;
    private EditText pass;
    private EditText address;
    private Button apply;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ActionBar actionBar = getSupportActionBar();

        name = (EditText)findViewById(R.id.editName);
        pass = (EditText)findViewById(R.id.editPass);
        address = (EditText)findViewById(R.id.editAddress);
        apply = (Button) findViewById(R.id.applysettings);

        name.setText(Bank.getInstance().getActiveuser().getName());
        pass.setText(Bank.getInstance().getActiveuser().getPassword());
        address.setText(Bank.getInstance().getActiveuser().getAddress());
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    setChanges();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }

public void setChanges() throws IOException {
    String oldname = Bank.getInstance().getActiveuser().getName();


    Bank.getInstance().getActiveuser().setName(name.getText().toString());
    Bank.getInstance().getActiveuser().setPassword(pass.getText().toString());
    Bank.getInstance().getActiveuser().setAddress(address.getText().toString());


    DataBaseHandler dataBaseHandler = new DataBaseHandler(this);
    dataBaseHandler.updateUserdata(oldname, Bank.getInstance().getActiveuser());

    name.setText(Bank.getInstance().getActiveuser().getName());
    pass.setText(Bank.getInstance().getActiveuser().getPassword());
    address.setText(Bank.getInstance().getActiveuser().getAddress());



}


}