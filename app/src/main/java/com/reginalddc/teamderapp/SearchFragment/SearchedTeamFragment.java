package com.reginalddc.teamderapp.SearchFragment;


import android.app.Activity;
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
import com.reginalddc.teamderapp.Model.UserCreateTeam;
import com.reginalddc.teamderapp.R;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchedTeamFragment extends Fragment {
    Spinner roles;
    String[] arraySpinner;
    Button requestJoin;
    Set<String> set = new HashSet<String>();
    private GoSearch _toSearch;
    int roleNum;
    int ctr = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_searched_team, container, false);
        TextView teamName = (TextView) fragmentView.findViewById(R.id.SearchedTeam_TextView);
        TextView teamDesc = (TextView) fragmentView.findViewById(R.id.textView_teamSearchedDesc);
        String strtext = getArguments().getString("teamNames");
        String strtext2 = getArguments().getString("teamDesc");
        final int teamId = Integer.parseInt(getArguments().getString("teamId"));
        final int userId = getArguments().getInt("userId");
        final String fullName = getArguments().getString("fullName");
        roleNum = getArguments().getStringArray("teamRoles").length - 1;
        for(int i = 0; i < roleNum; i++){
            set.add(getArguments().getStringArray("teamRoles")[i]);
        }
        arraySpinner = new String[set.size()];
        for (Iterator<String> it = set.iterator(); it.hasNext(); ) {
            arraySpinner[ctr] = it.next();
            ctr++;
        }

        //for(int i = 0;i<arraySpinner.length;i++){
          //  arraySpinner2[i] = arraySpinner[i+1];
        //}
        final RequestParams params = new RequestParams();
       // System.out.println(strtext);
        teamName.setText(strtext);
        teamDesc.setText(strtext2);
        roles = (Spinner)fragmentView.findViewById(R.id.spinnerRoles);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),
               android.R.layout.simple_dropdown_item_1line, arraySpinner);
       // ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),
         //                 android.R.layout.simple_dropdown_item_1line, arraySpinner2);
        roles.setAdapter(arrayAdapter);

        requestJoin = (Button) fragmentView.findViewById(R.id.btn_rqstJoin);
        requestJoin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                params.put("team_id",Integer.toString(teamId));
                params.put("user_id", Integer.toString(userId));
                params.put("role", roles.getSelectedItem().toString());
                params.put("name",fullName);
                System.out.print(teamId + " " + userId + " " + roles.getSelectedItem().toString());
                invokeWs(params);
            }
        });
        return fragmentView;
    }

    private void invokeWs(RequestParams params){
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://107.170.61.180/android/teamderived_api/requests/create_request.php", params, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(String response) {
                try {

                    JSONObject obj = new JSONObject(response);
                    System.out.println(obj);
                    if (obj.getBoolean("success")) {
                        Toast.makeText(getContext(), "you have successfuly requested to join", Toast.LENGTH_LONG).show();
                        _toSearch.toSearch();
                    } else {
                        Toast.makeText(getContext(), "Ooops! there is an error!", Toast.LENGTH_LONG).show();
                    }

                } catch (Exception e) {
                }
            }
        });
    }
    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);

        try{
            _toSearch = (GoSearch) activity;

        }catch (Exception ex){

            throw new RuntimeException(activity.toString() + " must implement UpdateProfile");

        }
    }

    @Override
    public void onDetach(){
        super.onDetach();
        _toSearch = null;
    }

    public interface GoSearch {
        public void toSearch();
    }

}
