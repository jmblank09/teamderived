package com.reginalddc.teamderapp.SearchFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.reginalddc.teamderapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchedTeamFragment extends Fragment {
    Spinner roles;
    String[] arraySpinner = {"Front-End", "Back-End", "Pitcher", "UX Designer"};

    public SearchedTeamFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_searched_team, container, false);
        TextView teamName = (TextView) fragmentView.findViewById(R.id.SearchedTeam_TextView);
        //String strtext = getArguments().getString("teamNames");
       // System.out.println(strtext);
        //teamName.setText(strtext);
        roles = (Spinner)fragmentView.findViewById(R.id.spinnerRoles);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, arraySpinner);
        roles.setAdapter(arrayAdapter);
        return fragmentView;
    }

}
