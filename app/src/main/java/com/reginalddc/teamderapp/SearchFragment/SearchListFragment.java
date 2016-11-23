package com.reginalddc.teamderapp.SearchFragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.reginalddc.teamderapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.zip.Inflater;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchListFragment extends ListFragment {

    private toGoToSearchedTeamFragment _mSendData;

    View rootView = null;
    String[] teamName = {"TeamBa", "Team2", "Team3"};
    ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
    SimpleAdapter adapterr;

    // TODO: Rename and change types of parameters
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        HashMap<String, String> map;
        for (int i = 0; i < teamName.length; i++) {
            map = new HashMap<String, String>();
            map.put("TeamNames", teamName[i]);

            data.add(map);
        }

        String [] keys = {"TeamNames"};

        int[] to ={R.id.TeamName_textView};
        adapterr = new SimpleAdapter(getActivity(), data, R.layout.listview_searchresults, keys, to);
        setListAdapter(adapterr);
        //doSearch();

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        getListView().setDivider(null);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart(){
        super.onStart();
        final EditText inputSearch = (EditText) getActivity().findViewById(R.id.editText_search);
        inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ((SimpleAdapter)SearchListFragment.this.adapterr).getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View v, int pos, long id) {
                String tv =  ((TextView) adapterr.getView(pos,v,null).findViewById((R.id.TeamName_textView))).getText().toString();
                _mSendData.toGoToSearchedTeamFragment(tv);
                Toast.makeText(getActivity(), tv, Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);

        try
        {
            _mSendData = (toGoToSearchedTeamFragment) activity;
        }
        catch(Exception ex)
        {
            throw new RuntimeException(activity.toString()
                    + " must implement OnSendData");
        }
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        _mSendData = null;
    }

    public interface toGoToSearchedTeamFragment
    {
        void toGoToSearchedTeamFragment(String data);
    }
}
