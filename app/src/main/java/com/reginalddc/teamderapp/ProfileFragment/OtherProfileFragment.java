package com.reginalddc.teamderapp.ProfileFragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.reginalddc.teamderapp.ManageFragment.RequestToJoinTeamFragment;
import com.reginalddc.teamderapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class OtherProfileFragment extends Fragment {
    private RequestToJoinTeamFragment.onBacktoManageTeam _toGoBacktoManageTeam;

    public OtherProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_other_profile, container, false);

        TextView backToManageTeam = (TextView)fragmentView.findViewById(R.id.btn_backToPrevious);
        backToManageTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _toGoBacktoManageTeam.toGotoManageTeam();
            }
        });

        return fragmentView;
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);

        try{
            _toGoBacktoManageTeam = (RequestToJoinTeamFragment.onBacktoManageTeam) activity;
        }catch (Exception ex){

            throw new RuntimeException(activity.toString() + " must implement onBacktoManageTeam");

        }
    }

    @Override
    public void onDetach(){
        super.onDetach();
        _toGoBacktoManageTeam = null;
    }

    public interface onBacktoManageTeam {

        public void toGotoManageTeam();
    }

}
