package com.reginalddc.teamderapp.HomePageFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.reginalddc.teamderapp.ManageFragment.ManageTeamFragment;
import com.reginalddc.teamderapp.ManageFragment.ViewTeamFragment;
import com.reginalddc.teamderapp.Model.CreatedTeamAdapter;
import com.reginalddc.teamderapp.Model.Team;
import com.reginalddc.teamderapp.Model.UserProfile;
import com.reginalddc.teamderapp.Model.UserTeam;
import com.reginalddc.teamderapp.R;

import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreatedTeamFragment extends Fragment {

    View fragmentView;
    public CreatedTeamFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_created_team, container, false);

        RequestParams params = new RequestParams();
        params.put("user_id", Integer.toString(UserProfile.getUserID()));
        invokeWS(params);

        return fragmentView;
    }

    public void invokeWS(RequestParams params){

        AsyncHttpClient client = new AsyncHttpClient();

        client.get("http://107.170.61.180/android/teamderived_api/teams/get_created_teams.php", params, new AsyncHttpResponseHandler(){

            @Override
            public void onSuccess(String response){
                try{
                    JSONObject obj = new JSONObject(response);
                    UserTeam userTeam = new UserTeam(obj);
                    userTeam.retrievalData();
                    willView();
                }catch (Exception e){}
            }
        });
    }

    private void willView(){

        ArrayList<Team> arrayOfTeam = new ArrayList<Team>();
        final CreatedTeamAdapter adapter = new CreatedTeamAdapter(getContext(), arrayOfTeam);
        ListView listView = (ListView) fragmentView.findViewById(R.id.listView_createdTeams);
        listView.setAdapter(adapter);
        final UserTeam userTeam = new UserTeam();
        final String arrayID[] = userTeam.getTeamID();
        String arrayName[] = userTeam.getTeamName();
        String arrayDesc[] = userTeam.getTeamDescription();
        if (arrayID.length > 0){

            for(int i = 0; i < arrayID.length; i++){
                Team addTeam = new Team(arrayName[i], arrayDesc[i]);
                adapter.add(addTeam);
            }

        }

        final FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                userTeam.setSelectedTeamID(arrayID[position]);
                fragmentManager.beginTransaction().replace(R.id.fragment_layout, new ManageTeamFragment()).commit();
            }
        });
    }
}
