package com.reginalddc.teamderapp.ManageFragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.reginalddc.teamderapp.Model.ManageTeamAdapter;
import com.reginalddc.teamderapp.Model.Team;
import com.reginalddc.teamderapp.Model.TeamMembers;
import com.reginalddc.teamderapp.Model.UserTeam;
import com.reginalddc.teamderapp.R;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ManageTeamFragment extends Fragment {

    View fragmentView;
    TextView backToCreatedTeam,goToRequestToJoinTeam, teamName, teamDesc;
    private onBacktoCreatedTeam _toGoBacktoCreatedTeam;
    private onGotoRequestTeam _toGoBacktoRequestTeam;

    public ManageTeamFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_manage, container, false);


        backToCreatedTeam = (TextView)fragmentView.findViewById(R.id.btn_backToCreatedTeam);
        backToCreatedTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _toGoBacktoCreatedTeam.toGotoCreatedTeam();
            }
        });

        goToRequestToJoinTeam = (TextView)fragmentView.findViewById(R.id.btn_tapHere);
        goToRequestToJoinTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _toGoBacktoRequestTeam.toGotoRequestTeam();
            }
        });

        RequestParams params = new RequestParams();
        params.put("team_id", UserTeam.getSelectedTeamID());
        invokeWS(params);
        return fragmentView;
    }

    public void invokeWS(RequestParams params){

        AsyncHttpClient client = new AsyncHttpClient();

        client.get("http://107.170.61.180/android/teamderived_api/teams/get_team_info.php", params, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(String response){
                try {
                    JSONObject obj = new JSONObject(response);
                    UserTeam userTeam = new UserTeam(obj);
                    userTeam.retrievalData2();
                    willView();
                }catch (Exception e) {}
            }
        });

        client.get("http://107.170.61.180/android/teamderived_api/members/get_members.php", params, new AsyncHttpResponseHandler(){

            @Override
            public void onSuccess(String response){
                try{
                    JSONObject obj = new JSONObject(response);
                    TeamMembers teamMembers = new TeamMembers(obj);
                    teamMembers.retrievalData();
                    willView();
                }catch (Exception e){}
            }
        });
    }


    private void willView(){

        teamName = (TextView) fragmentView.findViewById(R.id.textView_teamName);
        teamDesc = (TextView) fragmentView.findViewById(R.id.textView_teamDesc);

        teamName.setText(UserTeam.getSelectedTeamName());
        teamDesc.setText(UserTeam.getSelectedTeamDescription());

        ArrayList<Team> arrayOfTeam = new ArrayList<Team>();
        final ManageTeamAdapter adapter = new ManageTeamAdapter(getContext(), arrayOfTeam);
        ListView listView = (ListView) fragmentView.findViewById(R.id.listView_manageTeams);
        listView.setAdapter(adapter);

        String[] member_id = TeamMembers.getMemberID();
        String[] name = TeamMembers.getName();
        String[] role = TeamMembers.getRole();

        for (int i = 1; i < member_id.length; i++){
            Team addTeam = new Team(name[i], role[i]);
            adapter.add(addTeam);
        }

    }


    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);

        try{
            _toGoBacktoCreatedTeam = (onBacktoCreatedTeam) activity;
            _toGoBacktoRequestTeam = (onGotoRequestTeam) activity;
        }catch (Exception ex){

            throw new RuntimeException(activity.toString() + " must implement onBacktoCreatedTeam");

        }
    }

    @Override
    public void onDetach(){
        super.onDetach();
        _toGoBacktoCreatedTeam = null;
        _toGoBacktoRequestTeam = null;
    }

    public interface onBacktoCreatedTeam {

        public void toGotoCreatedTeam();
    }

    public interface onGotoRequestTeam {

        public void toGotoRequestTeam();
    }

}
