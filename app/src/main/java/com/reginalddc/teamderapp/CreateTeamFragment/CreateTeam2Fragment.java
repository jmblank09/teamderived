package com.reginalddc.teamderapp.CreateTeamFragment;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.reginalddc.teamderapp.Activity.TeamActivity;
import com.reginalddc.teamderapp.Model.UserCreateTeam;
import com.reginalddc.teamderapp.Model.UserProfile;
import com.reginalddc.teamderapp.R;

import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateTeam2Fragment extends Fragment {
    View fragmentView;
    private CreateTeam _toHome, _toGoBacktoCreateTeam;
    Spinner role1,role2,role3,role4,role5,role6;
    String[] arraySpinner = {"Front-End", "Back-End", "Researcher", "Pitcher", "UX Designer"};
    TextView textRole3, textRole4, textRole5, textRole6, textMem3, textMem4, textMem5, textMem6, teamLeader;
    Button btnCreate;

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

        btnCreate = (Button) fragmentView.findViewById(R.id.btn_CreateTeam);

        final RequestParams params = new RequestParams();

        switch (UserCreateTeam.getNumOfMembers()){
            case 2:
                hideThird();
                hideFourth();
                hideFifth();
                hideSixth();
                btnCreate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        UserCreateTeam.setRoles(role1.getSelectedItem().toString() + ";" + role2.getSelectedItem().toString() + ";");
                        params.put("user_id", Integer.toString(UserProfile.getUserID()));
                        params.put("name", UserCreateTeam.getTeamName());
                        params.put("description", UserCreateTeam.getTeamDesc());
                        params.put("capacity", Integer.toString(UserCreateTeam.getNumOfMembers()));
                        params.put("roles", UserCreateTeam.getRoles());
                        invokeWS(params);
                    }
                });
                break;
            case 3:
                hideFourth();
                hideFifth();
                hideSixth();
                btnCreate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        UserCreateTeam.setRoles(role1.getSelectedItem().toString() + ";" + role2.getSelectedItem().toString() + ";"
                                + role3.getSelectedItem().toString() + ";");
                        params.put("user_id", Integer.toString(UserProfile.getUserID()));
                        params.put("name", UserCreateTeam.getTeamName());
                        params.put("description", UserCreateTeam.getTeamDesc());
                        params.put("capacity", Integer.toString(UserCreateTeam.getNumOfMembers()));
                        params.put("roles", UserCreateTeam.getRoles());
                        invokeWS(params);
                    }
                });
                break;
            case 4:
                hideFifth();
                hideSixth();
                btnCreate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        UserCreateTeam.setRoles(role1.getSelectedItem().toString() + ";" + role2.getSelectedItem().toString() + ";"
                                + role3.getSelectedItem().toString() + ";" + role4.getSelectedItem().toString() + ";");
                        params.put("user_id", Integer.toString(UserProfile.getUserID()));
                        params.put("name", UserCreateTeam.getTeamName());
                        params.put("description", UserCreateTeam.getTeamDesc());
                        params.put("capacity", Integer.toString(UserCreateTeam.getNumOfMembers()));
                        params.put("roles", UserCreateTeam.getRoles());
                        invokeWS(params);
                    }
                });
                break;
            case 5:
                hideSixth();
                btnCreate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        UserCreateTeam.setRoles(role1.getSelectedItem().toString() + ";" + role2.getSelectedItem().toString() + ";"
                                + role3.getSelectedItem().toString() + ";" + role4.getSelectedItem().toString() + ";"
                                + role5.getSelectedItem().toString() + ";");
                        params.put("user_id", Integer.toString(UserProfile.getUserID()));
                        params.put("name", UserCreateTeam.getTeamName());
                        params.put("description", UserCreateTeam.getTeamDesc());
                        params.put("capacity", Integer.toString(UserCreateTeam.getNumOfMembers()));
                        params.put("roles", UserCreateTeam.getRoles());
                        invokeWS(params);
                    }
                });
                break;
            default:
                btnCreate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        UserCreateTeam.setRoles(role1.getSelectedItem().toString() + ";" + role2.getSelectedItem().toString() + ";"
                                + role3.getSelectedItem().toString() + ";" + role4.getSelectedItem().toString() + ";"
                                + role5.getSelectedItem().toString() + ";" + role6.getSelectedItem().toString() + ";");
                        params.put("user_id", Integer.toString(UserProfile.getUserID()));
                        params.put("name", UserCreateTeam.getTeamName());
                        params.put("description", UserCreateTeam.getTeamDesc());
                        params.put("capacity", Integer.toString(UserCreateTeam.getNumOfMembers()));
                        params.put("roles", UserCreateTeam.getRoles());
                        invokeWS(params);
                    }
                });
                break;
        }

        TextView backToCreateTeam = (TextView)fragmentView.findViewById(R.id.btn_backToCreateTeam);
        backToCreateTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _toGoBacktoCreateTeam.toGotoCreateTeam();
            }
        });

        return fragmentView;
    }

    public void invokeWS(RequestParams params) {
        AsyncHttpClient client = new AsyncHttpClient();

        client.get("http://107.170.61.180/android/teamderived_api/teams/create_team.php", params, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    if (obj.getBoolean("success")) {
                        Toast.makeText(getContext(), UserCreateTeam.getTeamName() + " has successfully created", Toast.LENGTH_LONG).show();
                        _toHome.toHome();
                    } else {
                        Toast.makeText(getContext(), "Ooops! there is an error!", Toast.LENGTH_LONG).show();
                    }


                } catch (Exception e) {
                }
            }
        });
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

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);

        try{
            _toGoBacktoCreateTeam = (CreateTeam) activity;
            _toHome = (CreateTeam) activity;

        }catch (Exception ex){

            throw new RuntimeException(activity.toString() + " must implement UpdateProfile");

        }
    }

    @Override
    public void onDetach(){
        super.onDetach();
        _toHome = null;
        _toGoBacktoCreateTeam = null;
    }

    public interface CreateTeam {
        public void toGotoCreateTeam();
        public void toHome();
    }

}
