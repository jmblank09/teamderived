package com.reginalddc.teamderapp.HomePageFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.reginalddc.teamderapp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.fragment_home, container, false);

        FragmentManager teamFragment = getChildFragmentManager();
        teamFragment.beginTransaction().replace(R.id.fragmentCreatedTeam_layout, new CreatedTeamFragment()).commit();
        teamFragment.beginTransaction().replace(R.id.fragmentJoinedTeam_layout, new JoinedTeamFragment()).commit();
        return fragmentView;
    }

}
