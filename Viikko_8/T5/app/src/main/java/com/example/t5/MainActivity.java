package com.example.t5;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private TextView screen;
    private  BottleDispenser pullomaatti;
    private TextView printscreen;
    private Button changeout;
    private SeekBar rahaslideri;
    private Spinner dropdown;
    private int selecteditem;
    private ArrayAdapter<Bottle> adapt;
    private String kuitti="";
    private Context context = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pullomaatti = BottleDispenser.getInstance();
        screen = (TextView) findViewById(R.id.textView);
        screen.setText(pullomaatti.printBottles());
        printscreen = (TextView) findViewById(R.id.textView2);
        changeout = (Button) findViewById(R.id.button2);
        context = MainActivity.this;

        Spinner dropdown = (Spinner) findViewById(R.id.spinner);
        dropdown .setOnItemSelectedListener(this);

        adapt = new ArrayAdapter<Bottle>(this, android.R.layout.simple_dropdown_item_1line, pullomaatti.getLista());
        dropdown.setAdapter(adapt);


        rahaslideri = (SeekBar) findViewById(R.id.seekBar);
        rahaslideri .setMax(4);
        rahaslideri .incrementProgressBy(1);

        rahaslideri.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            float progressChangedValue = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, "Raha määrä: " + progressChangedValue*0.5 +"€",
                        Toast.LENGTH_SHORT).show();
            }
        });
        rahaslideri .setProgress(0);
    }

    public void refreshScreen() {
        screen.setText("");
        screen.setText(pullomaatti.printBottles());

    }


    public void addMoney(View view) {
        printscreen.setText(pullomaatti.addMoney(rahaslideri.getProgress()*0.5));
        rahaslideri .setProgress(0);

    }

    public void changeout(View view) {
        printscreen.setText(pullomaatti.returnMoney());
    }

    public void buyBottle(View view) {
        printscreen.setText(pullomaatti.buyBottle(selecteditem));
        refreshScreen();

    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void kuitti(View view) {
        String date = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
        kuitti += pullomaatti.getReceipt();
        kuitti += date;
        try {
            OutputStreamWriter out = new OutputStreamWriter(context.openFileOutput("kuitti.txt", Context.MODE_PRIVATE));
            out.write(kuitti);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            System.out.println("FILE WRITTEN");
        }
    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        selecteditem = i;
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
