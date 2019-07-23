package com.example.bankapp_aj;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

public class TransferActivity extends AppCompatActivity {

    private Spinner to;
    private Spinner from;
    private TextView frommoney;
    private ArrayAdapter<Account> adapt2;
    private TextView tomoney;
    private int fromget;
    private int toget;
    private SeekBar seekbar;
    private Button acceptbutton;
    private TextView transfertext;
    private int transferamount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
        setTextViews();
        initCompontents();

    }



    public void initCompontents() {


        from = (Spinner) findViewById(R.id.fromspinner);
        to = (Spinner) findViewById(R.id.tospinner);
        seekbar = (SeekBar) findViewById(R.id.seekBar);
        acceptbutton = (Button) findViewById(R.id.acceptbutton);
        transfertext = (TextView) findViewById(R.id.transfertext);
        adapt2 = new ArrayAdapter<Account>(this, R.layout.support_simple_spinner_dropdown_item, Bank.getInstance().getActiveuser().getAccountlist());
        from.setAdapter(adapt2);
        to.setAdapter(adapt2);
        from.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                fromget = i;
                frommoney.setText(String.valueOf(Bank.getInstance().getActiveuser().getAccountlist().get(i).getMoneyamount())+"€");

                seekbar.setMax(Math.round(Bank.getInstance().getActiveuser().getAccountlist().get(fromget).getMoneyamount()));
                seekbar.setProgress(0);
                transfertext.setText(String.valueOf(0)+"€");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        to.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                toget = i;
                tomoney.setText(String.valueOf(Bank.getInstance().getActiveuser().getAccountlist().get(i).getMoneyamount())+"€");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        seekbar.setMax(Math.round(Bank.getInstance().getActiveuser().getAccountlist().get(0).getMoneyamount()));


        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                transferamount = i;
                transfertext.setText(String.valueOf(transferamount) +"€");


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        acceptbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                acceptTransfer(fromget,toget,transferamount);
                adapt2.notifyDataSetChanged();
                tomoney.setText(String.valueOf(Bank.getInstance().getActiveuser().getAccountlist().get(toget).getMoneyamount())+"€");
                frommoney.setText(String.valueOf(Bank.getInstance().getActiveuser().getAccountlist().get(fromget).getMoneyamount())+"€");
                seekbar.setProgress(0);
                transfertext.setText(String.valueOf(0)+"€");

            }
        });

    }

    public void setTextViews() {
        frommoney = findViewById(R.id.frommoney);
        tomoney = findViewById(R.id.tomoney);
    }

    public void acceptTransfer(int fromget, int toget, int transferamount) {
        if(transferamount!=0) {
            Bank.getInstance().getActiveuser().transferMoney(fromget, toget, transferamount);

        }
    }

}
