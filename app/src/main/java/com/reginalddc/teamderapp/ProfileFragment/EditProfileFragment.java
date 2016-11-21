package com.reginalddc.teamderapp.ProfileFragment;


import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.reginalddc.teamderapp.Activity.MainActivity;
import com.reginalddc.teamderapp.Model.UserProfile;
import com.reginalddc.teamderapp.R;

import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditProfileFragment extends Fragment {

    View fragmentView;
    private UpdateProfile _toProfile;
    EditText name, email, phone, birthday, school, course,
        yearLevel, mainRole, otherRole, achievements, extraCo;

    public EditProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_edit_profile, container, false);

        willView();
        Button btnUpdate = (Button) fragmentView.findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUser();
            }
        });

        return fragmentView;
    }

    public void updateUser(){
        final RequestParams params = new RequestParams();
        params.put("user_id", Integer.toString(UserProfile.getUserID()));
        params.put("name", name.getText().toString());
        params.put("email", email.getText().toString());
        params.put("password", UserProfile.getPassword());
        params.put("phone", phone.getText().toString());
        params.put("birthday", birthday.getText().toString());
        params.put("school", school.getText().toString());
        params.put("course", course.getText().toString());
        params.put("year_level", yearLevel.getText().toString());
        params.put("main_role", mainRole.getText().toString());
        params.put("other_roles", otherRole.getText().toString());
        params.put("achievements", achievements.getText().toString());
        params.put("extracuricular", extraCo.getText().toString());

        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(getContext());
        dlgAlert.setMessage("Are you sure you want to update your Profile?");
        dlgAlert.setPositiveButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //do nothing
                    }
                });

        dlgAlert.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                invokeWS(params);
            }
        });

        dlgAlert.setCancelable(true);
        dlgAlert.create().show();

    }

    public void invokeWS(RequestParams params){
        AsyncHttpClient client = new AsyncHttpClient();

        client.get("http://107.170.61.180/android/teamderived_api/users/edit_profile.php", params, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(String response){
                try{
                    JSONObject obj = new JSONObject(response);
                    if (obj.getBoolean("success")){
                        Toast.makeText(getContext(), "You've Successfully Updated your Profile", Toast.LENGTH_LONG).show();
                        _toProfile.toProfile();

                    }else{
                        Toast.makeText(getContext(), "Ooops! There is an error", Toast.LENGTH_LONG).show();
                    }
                }catch(Exception e){}
            }
        });
    }

    public void willView(){
        name = (EditText) fragmentView.findViewById(R.id.editText_name);
        email = (EditText) fragmentView.findViewById(R.id.editText_email);
        phone = (EditText) fragmentView.findViewById(R.id.editText_mobileNum);
        birthday = (EditText) fragmentView.findViewById(R.id.editText_birthday);
        school = (EditText) fragmentView.findViewById(R.id.editText_school);
        course = (EditText) fragmentView.findViewById(R.id.editText_course);
        yearLevel = (EditText) fragmentView.findViewById(R.id.editText_yearLevel);
        mainRole = (EditText) fragmentView.findViewById(R.id.editText_mainRole);
        otherRole = (EditText) fragmentView.findViewById(R.id.editText_preferredRole);
        achievements = (EditText) fragmentView.findViewById(R.id.editText_achievements);
        extraCo = (EditText) fragmentView.findViewById(R.id.editText_seminars);

        name.setText(UserProfile.getFullName());
        email.setText(UserProfile.getEmail());
        phone.setText(UserProfile.getPhoneNumber());
        birthday.setText(UserProfile.getBirthday());
        school.setText(UserProfile.getSchool());
        course.setText(UserProfile.getCourse());
        yearLevel.setText(UserProfile.getYearLevel());
        mainRole.setText(UserProfile.getMainRole());
        otherRole.setText(UserProfile.getOtherRole());
        achievements.setText(UserProfile.getAchievements());
        extraCo.setText(UserProfile.getExtraCo());

    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);

        try{

            _toProfile = (UpdateProfile) activity;

        }catch (Exception ex){

            throw new RuntimeException(activity.toString() + " must implement UpdateProfile");

        }
    }

    @Override
    public void onDetach(){
        super.onDetach();
        _toProfile = null;
    }

    public interface UpdateProfile {

        public void toProfile();
    }

}
