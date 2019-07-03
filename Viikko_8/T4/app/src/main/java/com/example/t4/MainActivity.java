package com.example.t4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private TextView screen;
    private  BottleDispenser pullomaatti;
    private TextView printscreen;
    private Button changeout;
    private SeekBar rahaslideri;
    private Spinner dropdown;
    private int selecteditem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pullomaatti = BottleDispenser.getInstance();
        screen = (TextView) findViewById(R.id.textView);
        screen.setText(pullomaatti.printBottles());
        printscreen = (TextView) findViewById(R.id.textView2);
        changeout = (Button) findViewById(R.id.button2);

        Spinner dropdown = (Spinner) findViewById(R.id.spinner);
        dropdown .setOnItemSelectedListener(this);

        ArrayAdapter<Bottle> adapt = new ArrayAdapter<Bottle>(this, android.R.layout.simple_dropdown_item_1line, pullomaatti.getLista());
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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        selecteditem = i;
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
