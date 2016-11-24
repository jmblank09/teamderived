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
import com.reginalddc.teamderapp.Model.Team;
import com.reginalddc.teamderapp.Model.ViewTeamAdapter;
import com.reginalddc.teamderapp.R;

import org.w3c.dom.Text;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewTeamFragment extends Fragment {
    TextView backToCreatedTeam;
    private onBacktoCreatedTeam _toGoBacktoCreatedTeam;

    public ViewTeamFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_view_team, container, false);

        ArrayList<Team> arrayOfTeam = new ArrayList<Team>();
        final ViewTeamAdapter adapter = new ViewTeamAdapter(getContext(), arrayOfTeam);
        ListView listView = (ListView) fragmentView.findViewById(R.id.listView_viewTeam);
        listView.setAdapter(adapter);
        Team firstTeam = new Team("Apolinario Mabini", "Front-End");
        Team secondTeam = new Team("Michael", "Back-End");
        Team thirdTeam = new Team("Zijugarat", "Taga tagay");
        adapter.add(firstTeam);
        adapter.add(secondTeam);
        adapter.add(thirdTeam);

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getContext(), adapter.getItem(position).teamName, Toast.LENGTH_LONG).show();
//            }
//        });

        backToCreatedTeam = (TextView)fragmentView.findViewById(R.id.btn_backToCreatedTeam);
        backToCreatedTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _toGoBacktoCreatedTeam.toGotoCreatedTeam();
            }
        });


        return fragmentView;
    }



    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);

        try{
            _toGoBacktoCreatedTeam = (onBacktoCreatedTeam) activity;
        }catch (Exception ex){

            throw new RuntimeException(activity.toString() + " must implement onBacktoCreatedTeam");

        }
    }

    @Override
    public void onDetach(){
        super.onDetach();
        _toGoBacktoCreatedTeam = null;
    }

    public interface onBacktoCreatedTeam {

        public void toGotoCreatedTeam();
    }

}
