package com.example.t2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;




import java.util.Calendar;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private TextView datetext;
    private TextView timetext;
    private DatePickerDialog.OnDateSetListener datelistener;
    private TimePickerDialog.OnTimeSetListener timelistener;
    private TheaterControl controller;
    private Spinner spinner;
    public ArrayAdapter<Theater> adapt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        initUiComp();
        initListerners();
        AsyncXMLParser parser = new AsyncXMLParser(adapt);
        parser.execute();




    }

public void initUiComp() {
    controller = TheaterControl.getInstance();


    datetext = (TextView) findViewById(R.id.textView2);
    timetext = (TextView) findViewById(R.id.textView3);


    spinner = (Spinner) findViewById(R.id.spinner);
    spinner.setOnItemSelectedListener(this);

    // Create an ArrayAdapter using the string array and a default spinner layout
     adapt = new ArrayAdapter<Theater>(this, android.R.layout.simple_dropdown_item_1line, controller.getLista());
    // Specify the layout to use when the list of choices appears
    adapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    // Apply the adapter to the spinner
    spinner.setAdapter(adapt);


}
public void initListerners() {

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
                    year, month, day);
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
            month += 1;
            Log.d("MainActivity", "onDateSet: date: " + day + "/" + month + "/" + year);
            String date = String.format("%d/%d/%d", day, month, year);
            datetext.setText(date);
        }
    };

}

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        spinner.setSelection(pos);
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

}



