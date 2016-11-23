package com.reginalddc.teamderapp.ProfileFragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.reginalddc.teamderapp.ManageFragment.RequestToJoinTeamFragment;
import com.reginalddc.teamderapp.Model.OtherProfile;
import com.reginalddc.teamderapp.Model.UserProfile;
import com.reginalddc.teamderapp.R;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class OtherProfileFragment extends Fragment {
    private RequestToJoinTeamFragment.onBacktoManageTeam _toGoBacktoManageTeam;

    View fragmentView;
    TextView fname, email, bday, number, school, course, year,
            mainRole, otherRole, achievements, seminar;

    public OtherProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_other_profile, container, false);

        TextView backToManageTeam = (TextView)fragmentView.findViewById(R.id.btn_backToPrevious);
        backToManageTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _toGoBacktoManageTeam.toGotoManageTeam();
            }
        });

        RequestParams params = new RequestParams();
        params.put("user_id", OtherProfile.getUserID());
        invokeWS(params);
        return fragmentView;
    }

    public void invokeWS(RequestParams params){
        AsyncHttpClient client = new AsyncHttpClient();

        client.get("http://107.170.61.180/android/teamderived_api/users/get_profile.php", params, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(String response){
                try{

                    JSONObject obj = new JSONObject(response);
                    OtherProfile user = new OtherProfile(obj);
                    user.retrievalData();
                    willView();
                }catch(Exception e){}
            }
        });
    }

    private void willView(){

        fname = (TextView) fragmentView.findViewById(R.id.editText_fname);
        email = (TextView) fragmentView.findViewById(R.id.editText_email);
        bday = (TextView) fragmentView.findViewById(R.id.editText_birthday);
        number = (TextView) fragmentView.findViewById(R.id.editText_mobileNumber);
        school = (TextView) fragmentView.findViewById(R.id.editText_school);
        course = (TextView) fragmentView.findViewById(R.id.editText_course);
        year = (TextView) fragmentView.findViewById(R.id.editText_yearLevel);
        mainRole = (TextView) fragmentView.findViewById(R.id.editText_mainRole);
        otherRole = (TextView) fragmentView.findViewById(R.id.editText_preferredRoles);
        achievements = (TextView) fragmentView.findViewById(R.id.editText_achievements);
        seminar = (TextView) fragmentView.findViewById(R.id.editText_seminars);

        fname.setText(UserProfile.getFullName());
        email.setText(UserProfile.getEmail());
        number.setText(UserProfile.getPhoneNumber());
        school.setText(UserProfile.getSchool());
        course.setText(UserProfile.getCourse());
        year.setText(UserProfile.getYearLevel());
        mainRole.setText(UserProfile.getMainRole());
        otherRole.setText(UserProfile.getOtherRole());
        achievements.setText(UserProfile.getAchievements());
        seminar.setText(UserProfile.getExtraCo());

        try {
            String dateString = UserProfile.getBirthday();
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = inputFormat.parse(dateString);
            SimpleDateFormat outputFormat = new SimpleDateFormat("MMMM dd, yyyy");
            String dateParsed = outputFormat.format(date);
            bday.setText(dateParsed);
        }catch (Exception e){}
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
