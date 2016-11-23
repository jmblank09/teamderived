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

import com.reginalddc.teamderapp.Model.ManageTeamAdapter;
import com.reginalddc.teamderapp.Model.RequestToJoinTeamAdapter;
import com.reginalddc.teamderapp.Model.Team;
import com.reginalddc.teamderapp.R;

import org.w3c.dom.Text;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class RequestToJoinTeamFragment extends Fragment {
    TextView backToManageTeam;
    private onBacktoManageTeam _toGoBacktoManageTeam;

    public RequestToJoinTeamFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_request_to_join, container, false);

        ArrayList<Team> arrayOfTeam = new ArrayList<Team>();
        final RequestToJoinTeamAdapter adapter = new RequestToJoinTeamAdapter(getContext(), arrayOfTeam);
        ListView listView = (ListView) fragmentView.findViewById(R.id.listView_requestToJoinTeam);
        listView.setAdapter(adapter);
        Team firstTeam = new Team("Alfredo Mercado", "Front-End");
        Team secondTeam = new Team("Reginald Dela Cruz", "Back-End");
        Team thirdTeam = new Team("Renato Decilos", "Pitcher");
        Team fourthTeam = new Team("Michael Isiderio", "Front-End");
        Team fifthTeam = new Team("Zishran Garces", "Back-End");
        adapter.add(firstTeam);
        adapter.add(secondTeam);
        adapter.add(thirdTeam);
        adapter.add(fourthTeam);
        adapter.add(fifthTeam);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), adapter.getItem(position).teamName, Toast.LENGTH_LONG).show();
            }
        });

        backToManageTeam = (TextView)fragmentView.findViewById(R.id.btn_backToManageTeam);
        backToManageTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _toGoBacktoManageTeam.toGotoManageTeam();
            }
        });

        return fragmentView;
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

        public void toGotoManageTeam();
    }


}
