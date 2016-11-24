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
import com.reginalddc.teamderapp.ManageFragment.ViewTeamFragment;
import com.reginalddc.teamderapp.Model.JoinedTeamAdapter;
import com.reginalddc.teamderapp.Model.Team;
import com.reginalddc.teamderapp.Model.UserProfile;
import com.reginalddc.teamderapp.Model.UserTeam;
import com.reginalddc.teamderapp.R;

import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class JoinedTeamFragment extends Fragment {

    View fragmentView;

    public JoinedTeamFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_joined_team, container, false);

        RequestParams params = new RequestParams();
        params.put("user_id", Integer.toString(UserProfile.getUserID()));
        invokeWS(params);

        return fragmentView;
    }

    private void invokeWS(RequestParams params){
        AsyncHttpClient client = new AsyncHttpClient();

        client.get("107.170.61.180/android/teamderived_api/teams/get_joined_teams.php", params, new AsyncHttpResponseHandler(){

            @Override
            public void onSuccess(String response){
                try{
                    JSONObject obj = new JSONObject(response);
                    UserTeam userTeam = new UserTeam(obj);
                    userTeam.retrievalJoinedTeam();
                    willView();
                }catch (Exception e){}
            }
        });
    }

    private void willView() {

        ArrayList<Team> arrayOfTeam = new ArrayList<Team>();
        final JoinedTeamAdapter adapter = new JoinedTeamAdapter(getContext(), arrayOfTeam);
        ListView listView = (ListView) fragmentView.findViewById(R.id.listView_joinedTeams);
        listView.setAdapter(adapter);
        final UserTeam userTeam = new UserTeam();
        final String arrayID[] = userTeam.getJoinedTeamID();
        String arrayName[] = userTeam.getJoinedTeamName();
        String arrayDesc[] = userTeam.getJoinedTeamDescription();

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
                UserTeam.setSelectedTeamID(arrayID[position]);
                fragmentManager.beginTransaction().replace(R.id.fragment_layout, new ViewTeamFragment()).commit();
            }
        });

    }

}
