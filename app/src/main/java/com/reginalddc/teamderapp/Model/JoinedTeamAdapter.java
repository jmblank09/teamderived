package com.reginalddc.teamderapp.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.reginalddc.teamderapp.Model.Team;
import com.reginalddc.teamderapp.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by reginalddc on 30/10/2016.
 */
public class JoinedTeamAdapter extends ArrayAdapter<Team> {

    public JoinedTeamAdapter(Context context, ArrayList<Team> team){
        super(context, 0, team);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        Team team = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_joinedteams, parent, false);
        }
        TextView viewTeam = (TextView) convertView.findViewById(R.id.textView_viewTeam);
        TextView teamName = (TextView) convertView.findViewById(R.id.textView_teamName);
        TextView teamDesc = (TextView) convertView.findViewById(R.id.textView_teamDesc);

        teamName.setText(team.teamName);
        teamDesc.setText(team.teamDesc);

        return convertView;
    }
}
