package com.example.t4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

//attejantunen

public class MainActivity extends AppCompatActivity {

    TextView text;
    EditText syotto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    public void setText(View v) {
        text.setText(syotto.getText().toString());


    }
}