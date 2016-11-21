package com.reginalddc.teamderapp.CreateTeamFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.reginalddc.teamderapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateTeam2Fragment extends Fragment {
    Spinner role1,role2,role3,role4;
    String[] arraySpinner = {"Front-End", "Back-End", "Researcher", "Pitcher", "UX Designer"};

    public CreateTeam2Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_create_team2, container, false);

        Spinner role1 = (Spinner)fragmentView.findViewById(R.id.spinner1);
        Spinner role2 = (Spinner)fragmentView.findViewById(R.id.spinner2);
        Spinner role3 = (Spinner)fragmentView.findViewById(R.id.spinner3);
        Spinner role4 = (Spinner)fragmentView.findViewById(R.id.spinner4);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, arraySpinner);
        role1.setAdapter(arrayAdapter);
        role2.setAdapter(arrayAdapter);
        role3.setAdapter(arrayAdapter);
        role4.setAdapter(arrayAdapter);

        return fragmentView;
    }

}
