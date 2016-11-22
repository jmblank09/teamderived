package com.reginalddc.teamderapp.CreateTeamFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.reginalddc.teamderapp.Model.UserCreateTeam;
import com.reginalddc.teamderapp.Model.UserProfile;
import com.reginalddc.teamderapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateTeam2Fragment extends Fragment {
    View fragmentView;
    Spinner role1,role2,role3,role4,role5,role6;
    String[] arraySpinner = {"Front-End", "Back-End", "Researcher", "Pitcher", "UX Designer"};
    TextView textRole3, textRole4, textRole5, textRole6, textMem3, textMem4, textMem5, textMem6, teamLeader;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_create_team2, container, false);

        role1 = (Spinner)fragmentView.findViewById(R.id.spinner1);
        role2 = (Spinner)fragmentView.findViewById(R.id.spinner2);
        role3 = (Spinner)fragmentView.findViewById(R.id.spinner3);
        role4 = (Spinner)fragmentView.findViewById(R.id.spinner4);
        role5 = (Spinner)fragmentView.findViewById(R.id.spinner5);
        role6 = (Spinner)fragmentView.findViewById(R.id.spinner6);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, arraySpinner);
        role1.setAdapter(arrayAdapter);
        role2.setAdapter(arrayAdapter);
        role3.setAdapter(arrayAdapter);
        role4.setAdapter(arrayAdapter);
        role5.setAdapter(arrayAdapter);
        role6.setAdapter(arrayAdapter);

        teamLeader = (TextView) fragmentView.findViewById(R.id.textView_teamLeader);
        teamLeader.setText(UserProfile.getFullName());

        switch (UserCreateTeam.getNumOfMembers()){
            case 2:
                hideThird();
                hideFourth();
                hideFifth();
                hideSixth();
                break;
            case 3:
                hideFourth();
                hideFifth();
                hideSixth();
                break;
            case 4:
                hideFifth();
                hideSixth();
                break;
            case 5:
                hideSixth();
                break;
            default: break;
        }

        return fragmentView;
    }

    public void hideThird(){
        //for third member text views and spinner
        textMem3 = (TextView) fragmentView.findViewById(R.id.editText_member3);
        textMem3.setVisibility(View.GONE);
        textRole3 = (TextView) fragmentView.findViewById(R.id.editText_role3);
        textRole3.setVisibility(View.GONE);
        role3.setVisibility(View.GONE);
    }

    public void hideFourth(){
        //for fourth member text views and spinner
        textMem4 = (TextView) fragmentView.findViewById(R.id.editText_member4);
        textMem4.setVisibility(View.GONE);
        textRole4 = (TextView) fragmentView.findViewById(R.id.editText_role4);
        textRole4.setVisibility(View.GONE);
        role4.setVisibility(View.GONE);
    }

    public void hideFifth(){
        //for fifth member text views and spinner
        textMem5 = (TextView) fragmentView.findViewById(R.id.editText_member5);
        textMem5.setVisibility(View.GONE);
        textRole5 = (TextView) fragmentView.findViewById(R.id.editText_role5);
        textRole5.setVisibility(View.GONE);
        role5.setVisibility(View.GONE);
    }

    public void hideSixth(){
        //for fifth member text views and spinner
        textMem6 = (TextView) fragmentView.findViewById(R.id.editText_member6);
        textMem6.setVisibility(View.GONE);
        textRole6 = (TextView) fragmentView.findViewById(R.id.editText_role6);
        textRole6.setVisibility(View.GONE);
        role6.setVisibility(View.GONE);
    }


}
