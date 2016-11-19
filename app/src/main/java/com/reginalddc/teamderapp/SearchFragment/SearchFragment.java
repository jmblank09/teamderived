package com.reginalddc.teamderapp.SearchFragment;


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
public class SearchFragment extends Fragment {


    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_search, container, false);
        FragmentManager teamFragment = getChildFragmentManager();
        teamFragment.beginTransaction().replace(R.id.Search_FrameLayout, new SearchListFragment()).commit();
        teamFragment.beginTransaction().replace(R.id.Searcher_FrameLayout, new SearchTabFragment()).commit();

        return fragmentView;
    }

}
