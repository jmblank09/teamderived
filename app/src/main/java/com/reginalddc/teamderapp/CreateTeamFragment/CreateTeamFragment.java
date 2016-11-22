package com.reginalddc.teamderapp.CreateTeamFragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.reginalddc.teamderapp.Model.UserProfile;
import com.reginalddc.teamderapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateTeamFragment extends Fragment {
    EditText members, teamName, teamDesc;
    TextView teamLeader;
    Button nextBtn;
    private onGoToCreateTeam2 _onGoToCreateTeam2;

    public CreateTeamFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View fragmentView = inflater.inflate(R.layout.fragment_create_team, container, false);
        members = (EditText)fragmentView.findViewById(R.id.editText_numOfMember);
        teamLeader = (TextView) fragmentView.findViewById(R.id.textView_teamLeader);
        teamName = (EditText) fragmentView.findViewById(R.id.editText_teamName);
        teamDesc = (EditText) fragmentView.findViewById(R.id.editText_desc);

        teamLeader.setText(UserProfile.getFullName());

        nextBtn = (Button)fragmentView.findViewById(R.id.nextBtn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _onGoToCreateTeam2.toGotoCreateTeam2();
            }
        });

        return fragmentView;
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);

        try{
            _onGoToCreateTeam2 = (onGoToCreateTeam2) activity;
        }catch (Exception ex){

            throw new RuntimeException(activity.toString() + " must implement onGoToCreateTeam2");

        }
    }

    @Override
    public void onDetach(){
        super.onDetach();
        _onGoToCreateTeam2 = null;
    }

    public interface onGoToCreateTeam2 {

        public void toGotoCreateTeam2();
    }

}
