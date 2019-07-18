package com.example.bankapp_aj;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * 7/18/19
 * AtteJantunen
 */
public class fragment_activity extends Fragment {

    private ArrayAdapter<AccountActivity> adaptactivity;
    private int Mainactivity_activedrop;
    private Spinner activitylist;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_activity, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Mainactivity_activedrop = ((MainActivity)getActivity()).getActivedropitem();
        activitylist = getActivity().findViewById(R.id.activityspinner);
        adaptactivity = new ArrayAdapter<AccountActivity>(getActivity(), R.layout.support_simple_spinner_dropdown_item, Bank.getInstance().getActiveuser().getAccountlist().get(Mainactivity_activedrop).getActivityList());
        activitylist.setAdapter(adaptactivity);



    }


    public void notifyChange() {
        if (Bank.getInstance().getActiveuser().getAccountlist().get(Mainactivity_activedrop).getActivityList() != null) {
            adaptactivity.notifyDataSetChanged();
        }
    }

}
