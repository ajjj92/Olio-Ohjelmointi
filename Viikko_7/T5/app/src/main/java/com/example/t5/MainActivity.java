package com.example.t5;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

//attejantunen

public class MainActivity extends AppCompatActivity {

    TextView text;
    EditText syotto;
    String filenimi;
    AlertDialog.Builder alert;
    Context context = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        text = (TextView) findViewById(R.id.textView2);
        syotto = (EditText) findViewById(R.id.editText);


        syotto.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //autogen
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //autogen
            }

            @Override
            public void afterTextChanged(Editable editable) {
                text.setText(syotto.getText().toString());
            }
        });





    }

    public void ShowAlertLataa(View view) {

        final EditText inputnimi;

        alert = new AlertDialog.Builder(this);
        alert.setTitle("ANNA TIEDOSTON NIMI");
        inputnimi = new EditText(this);
        alert.setView(inputnimi);

        alert.setPositiveButton("JATKA", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                filenimi = inputnimi.getText().toString();
                load();
            }
        });
        alert.setNegativeButton("PERU", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        }); alert.show();
    }

    public void ShowAlertTallenna(View view) {

        final EditText inputnimi;

        alert = new AlertDialog.Builder(this);
        alert.setTitle("ANNA TIEDOSTON NIMI");
        inputnimi = new EditText(this);
        alert.setView(inputnimi);

        alert.setPositiveButton("JATKA", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                filenimi = inputnimi.getText().toString();
                save();
            }
        });
        alert.setNegativeButton("PERU", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        }); alert.show();
    }

    public void load() {

        //dostuff
        try {

            InputStream in = context.openFileInput(filenimi);

            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String rivi = "";

            while ((rivi = br.readLine()) != null) {
                text.setText(rivi);
                System.out.println(rivi);
            }
            in.close();
        } catch (IOException e) {
            Log.e("IOException", "Virhe inputissa");
        } finally {
            System.out.println("LUETTU");
        }

    }

    public void save() {
        //dostuff
        try {
            OutputStreamWriter out = new OutputStreamWriter(context.openFileOutput(filenimi, Context.MODE_PRIVATE));
            String rivi = text.getText().toString();
            out.write(rivi);
            out.close();
        } catch (IOException e) {
            Log.e("IOException", "Virhe inputissa");
        } finally {
            System.out.println("KIRJOITETTU");
        }
    }
}

