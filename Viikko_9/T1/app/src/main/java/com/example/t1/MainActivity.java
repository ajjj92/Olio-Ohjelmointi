package com.example.t1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private TextView datetext;
    private TextView timetext;
    private DatePickerDialog.OnDateSetListener datelistener;
    private TimePickerDialog.OnTimeSetListener timelistener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        datetext = (TextView) findViewById(R.id.textView2);
        timetext = (TextView) findViewById(R.id.textView3);

        datetext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datedialogi = new DatePickerDialog(
                        MainActivity.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        datelistener,
                        year,month,day);
                datedialogi.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datedialogi.show();

            }
        });

        timetext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int min = c.get(Calendar.MINUTE);
                TimePickerDialog timedialogi = new TimePickerDialog(
                        MainActivity.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int min) {
                        timetext.setText(hour + ":" + min);
                    }
                }, hour, min, true);
                timedialogi.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timedialogi.show();

            }
        });

        datelistener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month +=1;
                Log.d("MainActivity", "onDateSet: date: " + day + "/" + month + "/" + year);
                String date = String.format("%d/%d/%d", day,month,year);
                datetext.setText(date);
            }
        };



        }



    }

