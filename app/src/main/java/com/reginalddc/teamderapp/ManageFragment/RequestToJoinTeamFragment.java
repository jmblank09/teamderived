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
import com.reginalddc.teamderapp.Model.RequestToJoinTeamAdapter;
import com.reginalddc.teamderapp.Model.Team;
import com.reginalddc.teamderapp.Model.TeamRequests;
import com.reginalddc.teamderapp.Model.UserTeam;
import com.reginalddc.teamderapp.R;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class RequestToJoinTeamFragment extends Fragment {

    View fragmentView;
    TextView backToManageTeam;
    private onBacktoManageTeam _toGoBacktoManageTeam;

    public RequestToJoinTeamFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_request_to_join, container, false);

        backToManageTeam = (TextView)fragmentView.findViewById(R.id.btn_backToManageTeam);
        backToManageTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _toGoBacktoManageTeam.backtoManageTeam();
            }
        });

        RequestParams params = new RequestParams();
        params.put("team_id", UserTeam.getSelectedTeamID());
        invokeWS(params);

        return fragmentView;
    }

    public void invokeWS(RequestParams params){
        AsyncHttpClient client = new AsyncHttpClient();

        client.get("http://107.170.61.180/android/teamderived_api/requests/get_requests.php", params, new AsyncHttpResponseHandler(){

            @Override
            public void onSuccess(String response){
                try{
                    JSONObject obj = new JSONObject(response);
                    TeamRequests teamRequests = new TeamRequests(obj);
                    teamRequests.retrievalData();
                    willView();
                }catch (Exception e){}
            }
        });
    }

    private void willView(){

        ArrayList<Team> arrayOfTeam = new ArrayList<Team>();
        final RequestToJoinTeamAdapter adapter = new RequestToJoinTeamAdapter(getContext(), arrayOfTeam);
        ListView listView = (ListView) fragmentView.findViewById(R.id.listView_requestToJoinTeam);
        listView.setAdapter(adapter);

        TeamRequests teamRequests = new TeamRequests();
        String[] request_id = teamRequests.getRequestID();
        String[] user_id = teamRequests.getUserID();
        String[] role = teamRequests.getRole();
        String[] name = teamRequests.getName();
        if (request_id.length > 0){

            for (int i = 0; i < request_id.length; i++){
                Team addTeam = new Team(name[i], role[i]);
                adapter.add(addTeam);
            }
        }
    }


    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);

        try{
            _toGoBacktoManageTeam = (onBacktoManageTeam) activity;
        }catch (Exception ex){

            throw new RuntimeException(activity.toString() + " must implement onBacktoManageTeam");

        }
    }

    @Override
    public void onDetach(){
        super.onDetach();
        _toGoBacktoManageTeam = null;
    }

    public interface onBacktoManageTeam {

        public void backtoManageTeam();
    }


}
