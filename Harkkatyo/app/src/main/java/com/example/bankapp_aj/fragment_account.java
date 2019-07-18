package com.example.bankapp_aj;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

/**
 * 7/16/19
 * AtteJantunen
 */
public class fragment_account extends Fragment {


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
    private float transferamount;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_account, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setTextViews();
        initCompontents();




    }



    public void initCompontents() {
        from = (Spinner) getActivity().findViewById(R.id.fromspinner);
        to = (Spinner) getActivity().findViewById(R.id.tospinner);
        seekbar = (SeekBar) getActivity().findViewById(R.id.seekBar);
        acceptbutton = (Button) getActivity().findViewById(R.id.acceptbutton);
        transfertext = (TextView) getActivity().findViewById(R.id.transfertext);
        adapt2 = new ArrayAdapter<Account>(getActivity(), R.layout.support_simple_spinner_dropdown_item, Bank.getInstance().getActiveuser().getAccountlist());
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


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                transfertext.setText(String.valueOf(transferamount) +"€");
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
               ((MainActivity)getActivity()).notifyChange();
               ((MainActivity)getActivity()).updateAdapter();

           }
       });

    }

    public void setTextViews() {
        frommoney = getActivity().findViewById(R.id.frommoney);
        tomoney = getActivity().findViewById(R.id.tomoney);
    }

    public void acceptTransfer(int fromget, int toget, float transferamount) {
        if(transferamount!=0) {
            Bank.getInstance().getActiveuser().transferMoney(fromget, toget, transferamount);

        }
        }

}
