package com.reginalddc.teamderapp.ProfileFragment;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.reginalddc.teamderapp.Activity.TeamActivity;
import com.reginalddc.teamderapp.HomePageFragment.HomeFragment;
import com.reginalddc.teamderapp.Model.UserProfile;
import com.reginalddc.teamderapp.R;

import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    View fragmentView;
    private OnEditProfile _toEditProfile, _logout;
    TextView fname, email, bday, number, school, course, year,
        mainRole, otherRole, achievements, seminar;
    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_profile, container, false);

        ImageView editProfile = (ImageView) fragmentView.findViewById(R.id.imageView_editProfile);
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _toEditProfile.toEditProfile();
            }
        });

        Button btnLogout = (Button) fragmentView.findViewById(R.id.btn_Logout);
        btnLogout.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                _logout.logout();
            }
        });

        RequestParams params = new RequestParams();
        params.put("user_id", Integer.toString(UserProfile.getUserID()));
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
                    UserProfile user = new UserProfile(obj);
                    user.retrievalData();
                }catch(Exception e){
                    Toast.makeText(getContext(), "Error Occured!", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });
    }

    public void willView(){

        fname = (TextView) fragmentView.findViewById(R.id.editText_fname);
        email = (TextView) fragmentView.findViewById(R.id.editText_email);
        bday = (TextView) fragmentView.findViewById(R.id.editText_birthday);

    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);

        try{
            _toEditProfile = (OnEditProfile) activity;
            _logout = (OnEditProfile) activity;
        }catch (Exception ex){

            throw new RuntimeException(activity.toString() + " must implement OnEditProfile");

        }
    }

    @Override
    public void onDetach(){
        super.onDetach();
        _toEditProfile = null;
        _logout = null;
    }

    public interface OnEditProfile {

        public void toEditProfile();
        public void logout();
    }

}
