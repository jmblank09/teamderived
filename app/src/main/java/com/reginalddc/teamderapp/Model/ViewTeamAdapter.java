package com.reginalddc.teamderapp.Model;

import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.reginalddc.teamderapp.Model.Team;
import com.reginalddc.teamderapp.ProfileFragment.OtherProfileFragment;
import com.reginalddc.teamderapp.R;

import java.util.ArrayList;

/**
 * Created by reginalddc on 30/10/2016.
 */
public class ViewTeamAdapter extends ArrayAdapter<Team> {

    public ViewTeamAdapter(Context context, ArrayList<Team> team){
        super(context, 0, team);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        Team team = getItem(position);
        String[] user_id = TeamMembers.getUserID();
        OtherProfile.setUserID(user_id[position]);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_viewteam, parent, false);
        }
        TextView viewProfile = (TextView)convertView.findViewById(R.id.btn_viewProfile);
        final FragmentTransaction fragmentManager = ((AppCompatActivity)getContext()).getSupportFragmentManager()
                .beginTransaction();
        viewProfile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getContext(), "You Pressed View Profile Button", Toast.LENGTH_LONG).show();
                fragmentManager.replace(R.id.fragment_layout, new OtherProfileFragment()).commit();
            }
        });

        TextView memName = (TextView) convertView.findViewById(R.id.textView_memName);
        TextView memDesc = (TextView) convertView.findViewById(R.id.textView_memDesc);

        memName.setText(team.teamName);
        memDesc.setText(team.teamDesc);

        return convertView;
    }
}
