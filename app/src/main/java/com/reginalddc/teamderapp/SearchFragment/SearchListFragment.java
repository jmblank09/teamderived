package com.reginalddc.teamderapp.SearchFragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.reginalddc.teamderapp.R;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchListFragment extends ListFragment {


    View rootView = null;
    String[] teamName = {"TeamBa", "Team2", "Team3"};
    ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
    SimpleAdapter adapter;
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
        adapter = new SimpleAdapter(getActivity(), data, R.layout.listview_searchresults, keys, to);
        setListAdapter(adapter);
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
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View v, int pos, long id) {
                Toast.makeText(getActivity(), data.get(pos).get("TeamNames"), Toast.LENGTH_SHORT).show();

            }
        });
    }
    // TODO: Rename method, update argument and hook method into UI event
    /** private void doSearch() {
     final EditText et = (EditText)rootView.findViewById(R.id.search_editText);
     et.addTextChangedListener(new TextWatcher() {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
    String text = et.getText().toString().toLowerCase(Locale.getDefault());
    filter(text);
    }
    });
     }
     public void filter(String charText) {
     charText = charText.toLowerCase(Locale.getDefault());
     HashMap<String, String> map;
     data .clear();
     if (charText.length() == 0) {
     for (int i = 0; i < teamName.length; i++) {
     map = new HashMap<String, String>();
     map.put("TeamNames", teamName[i]);

     data.add(map);
     }
     } else {
     for (int i = 0; i < teamName.length;i++){
     if(teamName[i].toLowerCase(Locale.getDefault()).contains(charText)){
     map = new HashMap<String, String>();
     map.put("TeamNames",teamName[i]);

     data.add(map);
     }
     }
     }
     adapter.notifyDataSetChanged();
     }**/
}
