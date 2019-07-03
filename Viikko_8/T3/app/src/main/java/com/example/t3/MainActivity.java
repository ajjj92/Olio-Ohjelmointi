package com.example.t3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView screen;
    private  BottleDispenser pullomaatti;
    private TextView printscreen;
    private Button changeout;
    private SeekBar rahaslideri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pullomaatti = BottleDispenser.getInstance();
        screen = (TextView) findViewById(R.id.textView);
        screen.setText(pullomaatti.printBottles());
        printscreen = (TextView) findViewById(R.id.textView2);
        changeout = (Button) findViewById(R.id.button2);
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

        switch (view.getId()) {
            case R.id.pullo1:
                printscreen.setText(pullomaatti.buyBottle(0));
                break;
            case R.id.pullo2:
                printscreen.setText(pullomaatti.buyBottle(1));
                break;
            case R.id.pullo3:
                printscreen.setText(pullomaatti.buyBottle(2));
                break;
            case  R.id.pullo4:
                printscreen.setText(pullomaatti.buyBottle(3));
                break;
            case R.id.pullo5:
                printscreen.setText(pullomaatti.buyBottle(4));
                break;
            case R.id.pullo6:
                printscreen.setText(pullomaatti.buyBottle(5));
                break;
        }
        refreshScreen();

    }
}
