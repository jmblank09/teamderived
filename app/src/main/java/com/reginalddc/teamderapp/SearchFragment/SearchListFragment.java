package com.reginalddc.teamderapp.SearchFragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.reginalddc.teamderapp.Model.UnfullCreatedTeams;
import com.reginalddc.teamderapp.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.zip.Inflater;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchListFragment extends ListFragment {

    private toGoToSearchedTeamFragment _mSendData;


    View rootView = null;
    ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
    SimpleAdapter adapterr;
    // TODO: Rename and change types of parameters
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        invokeWs();

        //doSearch();

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void invokeGetMembers(final UnfullCreatedTeams unfC, final ArrayList<UnfullCreatedTeams> teamss, final int checker){

        AsyncHttpClient team = new AsyncHttpClient();
        final RequestParams params = new RequestParams();

        params.put("team_id",Integer.toString(unfC.getTeamId()));

        team.get("http://107.170.61.180/android/teamderived_api/members/get_members.php",params, new AsyncHttpResponseHandler(){
            @Override
            public void onSuccess(String response){
                try{
                    boolean check = false;
                    int numberOfNoRoles;
                   int ctr = 0;
                    JSONArray json2 = (new JSONObject(response)).getJSONArray("members");
                    for(int i = 0; i< json2.length();i++){
                        looper:
                        if(!(json2.getJSONObject(i).getString("role").equals("Leader"))){
                            for(int j = 0; j < unfC.getCapacity(); j++){
                                if((json2.getJSONObject(i)).getString("role").equals((unfC.getRoles())[j])) {
                                    if(ctr == 0){
                                        unfC.setMembers((json2.getJSONObject(i)).getString("name"), j - 1);
                                        System.out.println((json2.getJSONObject(i)).getString("name"));
                                        ctr++;
                                        break looper;
                                    }
                                    if(unfC.getMembers(j - 1).equals("")){
                                        unfC.setMembers((json2.getJSONObject(i)).getString("name"), j - 1);
                                        break looper;
                                    }
                                    //used = j;
                                }
                            }
                        }
                    }
                    /*numberOfNoRoles = capacity - json2.length();
                    System.out.println(numberOfNoRoles);
                    for(int i = 0; i < numberOfNoRoles; i++){
                        members[i + json2.length()] = "None";
                    }*/
                    unfC.checkNoMembers();
                    if(checker == 1) {
                        setTeamss(teamss);

                    }
                }catch(Exception e){}
            }
        });
        //System.out.println(members[0] + "gago");
    }

    private void invokeWs(){
        AsyncHttpClient team = new AsyncHttpClient();

        final ArrayList<UnfullCreatedTeams> map = new ArrayList<UnfullCreatedTeams>();
        team.get("http://107.170.61.180/android/teamderived_api/teams/get_unfull_teams.php", new AsyncHttpResponseHandler(){
           @Override
            public void onSuccess(String response){
               try{
                   JSONArray json2 = (new JSONObject(response)).getJSONArray("teams");
                   for(int i = 0; i < json2.length(); i++){
                       UnfullCreatedTeams createdTeams = new UnfullCreatedTeams();
                       createdTeams.retrievalData(json2.getJSONObject(i));
                        map.add(createdTeams);
                   }
                   for(int i = 0; i < map.size() - 1; i++) {
                       invokeGetMembers(map.get(i), map, 0);
                   }
                   invokeGetMembers(map.get(map.size() - 1), map, 1);
               }catch(Exception e){}
           }
        });
    }
    private void setTeamss(ArrayList<UnfullCreatedTeams> teamss){
        HashMap<String, String> map;
        for (int i = 0; i < teamss.size(); i++) {
            map = new HashMap<String, String>();
            map.put("TeamId", Integer.toString(teamss.get(i).getTeamId()));
            map.put("TeamNames", teamss.get(i).getTeamName());
            map.put("TeamDescriptions", teamss.get(i).getTeamDescription());
             for(int j = 0; j < 6; j++){
                String role;
                if( j < teamss.get(i).getCapacity() + 1) {
                    role = teamss.get(i).getRoles()[j];
                    switch (j){
                        case 0: map.put("role1", role);
                            map.put("role1name", teamss.get(i).getTeamLeader());
                            break;
                        case 1: map.put("role2", role);
                            map.put("role2name",teamss.get(i).getMembers(j - 1));
                            break;
                        case 2: map.put("role3", role);
                            map.put("role3name",teamss.get(i).getMembers(j - 1));
                            break;
                        case 3: map.put("role4", role);
                            map.put("role4name",teamss.get(i).getMembers(j - 1));
                            break;
                        case 4: map.put("role5", role);
                            map.put("role5name",teamss.get(i).getMembers(j - 1));
                            break;
                        case 5: map.put("role6", role);
                            map.put("role6name",teamss.get(i).getMembers(j - 1));
                            break;
                        case 6:  map.put("role7", role);
                            map.put("role7name",teamss.get(i).getMembers(j - 1));
                            break;
                    }
                }else{
                    role = "";
                    switch (j){
                        case 0: map.put("role1", role);
                            break;
                        case 1: map.put("role2", role);
                            map.put("role2name","");
                            break;
                        case 2: map.put("role3", role);
                            map.put("role3name","");
                            break;
                        case 3: map.put("role4", role);
                            map.put("role4name","");
                            break;
                        case 4: map.put("role5", role);
                            map.put("role5name","");
                            break;
                        case 5: map.put("role6", role);
                            map.put("role6name","");
                            break;
                        case 6:  map.put("role7", role);
                            map.put("role7name","");
                            break;
                    }
                }
            }
            data.add(map);
        }

        String [] keys = {"TeamId","TeamNames", "TeamDescriptions", "role1", "role1name", "role2", "role2name"
                , "role3", "role3name", "role4", "role4name", "role5", "role5name", "role6", "role6name", "role7", "role7name"};
        //String [] keys = {"TeamNames", "TeamDescriptions"};
        int[] to ={R.id.textView_teamIdsearch, R.id.TeamName_textView, R.id.textView_teamDescript, R.id.textView_role1,
                R.id.role1name_textVIew, R.id.textView_role2, R.id.role2name_textView, R.id.textView_role3, R.id.role3name_textView,
                R.id.textView_role4, R.id.role4name_textView, R.id.textView_role5, R.id.role5name_textView, R.id.textView_role6,
                R.id.role6name_textView, R.id.textView_role7, R.id.role7name_textView};
        //int[] to ={R.id.TeamName_textView, R.id.textView_teamDescript};
        adapterr = new SimpleAdapter(getActivity(), data, R.layout.listview_searchresults, keys, to);
        //adapterr = new ArrayAdapter(getActivity(), R.layout.listview_searchresults, R.id.textView_teamIdsearch, data);
        setListAdapter(adapterr);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        getListView().setDivider(null);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart(){
        super.onStart();
        final EditText inputSearch = (EditText) getActivity().findViewById(R.id.editText_search);
        inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ((SimpleAdapter)SearchListFragment.this.adapterr).getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View v, int pos, long id) {
                String tv =  ((TextView) adapterr.getView(pos,v,null).findViewById((R.id.TeamName_textView))).getText().toString();
                String tv2 =  ((TextView) adapterr.getView(pos,v,null).findViewById((R.id.textView_teamDescript))).getText().toString();
                String [] tv3 = {"","","","","","", ""};
                if(((TextView) adapterr.getView(pos,v,null).findViewById((R.id.textView_role1))).getText().toString().equals("")){
                    tv3[0] = "";
                }else {
                    tv3[0] = ((TextView) adapterr.getView(pos, v, null).findViewById((R.id.textView_role1))).getText().toString();
                }
                if(((TextView) adapterr.getView(pos,v,null).findViewById((R.id.textView_role2))).getText().toString().equals("")){
                    tv3[1] = "";
                }else {
                    tv3[1] = ((TextView) adapterr.getView(pos, v, null).findViewById((R.id.textView_role2))).getText().toString();
                }
                if(((TextView) adapterr.getView(pos,v,null).findViewById((R.id.textView_role3))).getText().toString().equals("")){
                    tv3[2] = "";
                }else{
                    tv3[2] = ((TextView) adapterr.getView(pos,v,null).findViewById((R.id.textView_role3))).getText().toString();
                }
                if(((TextView) adapterr.getView(pos,v,null).findViewById((R.id.textView_role4))).getText().toString().equals("")){
                    tv3[3] = "";
                }else{
                    tv3[3] = ((TextView) adapterr.getView(pos,v,null).findViewById((R.id.textView_role4))).getText().toString();
                }
                if(((TextView) adapterr.getView(pos,v,null).findViewById((R.id.textView_role5))).getText().toString().equals("")){
                    tv3[3] = "";
                }else{
                    tv3[4] = ((TextView) adapterr.getView(pos,v,null).findViewById((R.id.textView_role5))).getText().toString();
                }
                if(((TextView) adapterr.getView(pos,v,null).findViewById((R.id.textView_role6))).getText().toString().equals("")){
                    tv3[5] = "";
                }else{
                    tv3[5] = ((TextView) adapterr.getView(pos,v,null).findViewById((R.id.textView_role6))).getText().toString();
                }
                if(((TextView) adapterr.getView(pos,v,null).findViewById((R.id.textView_role7))).getText().toString().equals("")){
                      tv3[6] = "";
                }else{
                    tv3[6] = ((TextView) adapterr.getView(pos,v,null).findViewById((R.id.textView_role7))).getText().toString();
                }
                String tv4 = ((TextView) adapterr.getView(pos,v,null).findViewById((R.id.textView_teamIdsearch))).getText().toString();
                _mSendData.toGoToSearchedTeamFragment(tv, tv2, tv3, tv4);
                Toast.makeText(getActivity(), tv, Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);

        try
        {
            _mSendData = (toGoToSearchedTeamFragment) activity;
        }
        catch(Exception ex)
        {
            throw new RuntimeException(activity.toString()
                    + " must implement OnSendData");
        }
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        _mSendData = null;
    }

    public interface toGoToSearchedTeamFragment
    {
        void toGoToSearchedTeamFragment(String data, String data2, String [] data3, String data4);
    }
}
