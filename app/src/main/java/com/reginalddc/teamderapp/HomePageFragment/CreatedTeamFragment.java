package com.reginalddc.teamderapp.HomePageFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.reginalddc.teamderapp.Model.CreatedTeamAdapter;
import com.reginalddc.teamderapp.Model.Team;
import com.reginalddc.teamderapp.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreatedTeamFragment extends Fragment {


    public CreatedTeamFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_created_team, container, false);

        ArrayList<Team> arrayOfTeam = new ArrayList<Team>();
        final CreatedTeamAdapter adapter = new CreatedTeamAdapter(getContext(), arrayOfTeam);
        ListView listView = (ListView) fragmentView.findViewById(R.id.listView_createdTeams);
        listView.setAdapter(adapter);
        Team firstTeam = new Team("TeamBA", "Team taga tambol");
        Team secondTeam = new Team("TeamRocket", "Jessie and James");
        adapter.add(firstTeam);
        adapter.add(secondTeam);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), adapter.getItem(position).teamName, Toast.LENGTH_LONG).show();
            }
        });

        return fragmentView;
    }

}
