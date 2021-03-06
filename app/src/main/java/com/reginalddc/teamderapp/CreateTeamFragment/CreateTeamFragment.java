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
import android.widget.Toast;

import com.reginalddc.teamderapp.Model.UserCreateTeam;
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

                String member = members.getText().toString();
                String teamN = teamName.getText().toString();
                String teamD = teamDesc.getText().toString();

                if (!(member.matches("")) && !(teamN.matches("")) && !(teamD.matches(""))) {

                    if(Integer.parseInt(member) > 1 && Integer.parseInt(member) < 7) {
                        UserCreateTeam.setNumOfMembers(Integer.parseInt(member));
                        UserCreateTeam.setTeamName(teamN);
                        UserCreateTeam.setTeamDesc(teamD);
                        _onGoToCreateTeam2.toGotoCreateTeam2();
                    }else{
                        Toast.makeText(getContext(), "Members only ranges from 2 up to 6!", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Kindly complete all the fields!", Toast.LENGTH_LONG).show();
                }
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
