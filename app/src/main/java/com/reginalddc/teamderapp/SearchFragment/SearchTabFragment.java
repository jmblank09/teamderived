package com.reginalddc.teamderapp.SearchFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.reginalddc.teamderapp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchTabFragment extends Fragment {


    public SearchTabFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragmen
        return inflater.inflate(R.layout.fragment_search_tab, container, false);
    }

}
