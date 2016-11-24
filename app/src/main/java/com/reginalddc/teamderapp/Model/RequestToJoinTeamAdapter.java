package com.reginalddc.teamderapp.Model;

import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.reginalddc.teamderapp.Model.Team;
import com.reginalddc.teamderapp.ProfileFragment.OtherProfileFragment;
import com.reginalddc.teamderapp.R;

import java.util.ArrayList;

/**
 * Created by reginalddc on 30/10/2016.
 */
public class RequestToJoinTeamAdapter extends ArrayAdapter<Team> {

    public RequestToJoinTeamAdapter(Context context, ArrayList<Team> team){
        super(context, 0, team);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){

        Team team = getItem(position);



        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_requesttojointeam, parent, false);
        }

        TextView viewProfile = (TextView)convertView.findViewById(R.id.btn_viewProfile);
        final FragmentTransaction fragmentManager = ((AppCompatActivity)getContext()).getSupportFragmentManager()
                .beginTransaction();
        viewProfile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String[] user_id = TeamRequests.getUserID();
                OtherProfile.setUserID(user_id[position]);
                OtherProfile.setTracer(3);
                fragmentManager.replace(R.id.fragment_layout, new OtherProfileFragment()).commit();
            }
        });

        ImageView deleteMember = (ImageView)convertView.findViewById(R.id.btn_delete);
        deleteMember.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getContext(), "You Pressed Delete Button", Toast.LENGTH_LONG).show();
            }
        });

        ImageView approveMember = (ImageView)convertView.findViewById(R.id.btn_approve);
        approveMember.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getContext(), "You Pressed Approve Button", Toast.LENGTH_LONG).show();
            }
        });

        TextView memName = (TextView) convertView.findViewById(R.id.textView_memName);
        TextView memDesc = (TextView) convertView.findViewById(R.id.textView_memDesc);

        memName.setText(team.teamName);
        memDesc.setText(team.teamDesc);

        return convertView;
    }
}
