package com.reginalddc.teamderapp.Model;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.RequestParams;
import com.reginalddc.teamderapp.Model.Team;
import com.reginalddc.teamderapp.ProfileFragment.OtherProfileFragment;
import com.reginalddc.teamderapp.ProfileFragment.ProfileFragment;
import com.reginalddc.teamderapp.R;

import java.util.ArrayList;

/**
 * Created by reginalddc on 30/10/2016.
 */
public class ManageTeamAdapter extends ArrayAdapter<Team> {

    ProgressDialog prgDialog;

    public ManageTeamAdapter(Context context, ArrayList<Team> team){
        super(context, 0, team);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){

        Team team = getItem(position);

        prgDialog = new ProgressDialog(getContext());
        prgDialog.setTitle("Processing...");
        prgDialog.setMessage("Please wait...");
        prgDialog.setCancelable(false);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_manageteam, parent, false);
        }
        TextView viewProfile = (TextView)convertView.findViewById(R.id.btn_viewProfile);
        final FragmentTransaction fragmentManager = ((AppCompatActivity)getContext()).getSupportFragmentManager()
                .beginTransaction();
        viewProfile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String[] user_id = TeamMembers.getUserID();
                OtherProfile.setUserID(user_id[position]);
                OtherProfile.setTracer(2);
                fragmentManager.replace(R.id.fragment_layout, new OtherProfileFragment()).commit();
            }
        });

        ImageView deleteMember = (ImageView)convertView.findViewById(R.id.btn_delete);
        deleteMember.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getContext(), "You Pressed Delete Button", Toast.LENGTH_LONG).show();
            }
        });

        TextView memName = (TextView) convertView.findViewById(R.id.textView_memName);
        TextView memDesc = (TextView) convertView.findViewById(R.id.textView_memDesc);

        memName.setText(team.teamName);
        memDesc.setText(team.teamDesc);

        return convertView;
    }

    private void invokeWS(RequestParams params){
        
    }


}
