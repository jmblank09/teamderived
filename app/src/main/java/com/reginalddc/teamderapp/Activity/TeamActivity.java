package com.reginalddc.teamderapp.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.reginalddc.teamderapp.CreateTeamFragment.CreateTeam2Fragment;
import com.reginalddc.teamderapp.CreateTeamFragment.CreateTeamFragment;
import com.reginalddc.teamderapp.HomePageFragment.HomeFragment;
import com.reginalddc.teamderapp.ManageFragment.ManageTeamFragment;
import com.reginalddc.teamderapp.ManageFragment.RequestToJoinTeamFragment;
import com.reginalddc.teamderapp.ManageFragment.ViewTeamFragment;
import com.reginalddc.teamderapp.Model.UserProfile;
import com.reginalddc.teamderapp.ProfileFragment.EditProfileFragment;
import com.reginalddc.teamderapp.ProfileFragment.ProfileFragment;
import com.reginalddc.teamderapp.R;
import com.reginalddc.teamderapp.SearchFragment.SearchFragment;
import com.reginalddc.teamderapp.SearchFragment.SearchListFragment;
import com.reginalddc.teamderapp.SearchFragment.SearchedTeamFragment;

import org.json.JSONObject;

public class TeamActivity extends AppCompatActivity implements ProfileFragment.OnEditProfile, EditProfileFragment.UpdateProfile,ManageTeamFragment.onBacktoCreatedTeam,ManageTeamFragment.onGotoRequestTeam,
        RequestToJoinTeamFragment.onBacktoManageTeam , ViewTeamFragment.onBacktoCreatedTeam, CreateTeamFragment.onGoToCreateTeam2, SearchListFragment.toGoToSearchedTeamFragment, CreateTeam2Fragment.CreateTeam {


    ImageView userIcon, createIcon, teamIcon, searchIcon;
    UserProfile user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);
        willView();
        RequestParams params = new RequestParams();
        params.put("user_id", Integer.toString(UserProfile.getUserID()));
        invokeWS(params);
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
                }catch(Exception e){}
            }
        });
    }

    @Override
    public void toProfile(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_layout, new ProfileFragment()).commit();
    }

    @Override
    public void logout(){
        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
        dlgAlert.setMessage("Are you sure you want to Log Out?");
        dlgAlert.setPositiveButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //do nothing
                    }
                });

        dlgAlert.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        dlgAlert.setCancelable(true);
        dlgAlert.create().show();

    }

    @Override
    public void toEditProfile(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_layout, new EditProfileFragment()).commit();
    }

    @Override
    public void toGotoCreatedTeam(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_layout, new HomeFragment()).commit();
    }

    @Override
    public void toGotoManageTeam(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_layout, new ManageTeamFragment()).commit();
    }

    @Override
    public void toGotoRequestTeam(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_layout, new RequestToJoinTeamFragment()).commit();
    }

    @Override
    public void toGotoCreateTeam2(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_layout, new CreateTeam2Fragment()).commit();
    }

    @Override
    public void toGotoCreateTeam(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_layout, new CreateTeamFragment()).commit();
    }

    @Override
    public void toGoToSearchedTeamFragment(String data){
        Bundle bundle=new Bundle();
        bundle.putString("teamNames", data);
        SearchedTeamFragment fragmentObject = new SearchedTeamFragment();
        fragmentObject.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_layout, fragmentObject).commit();
    }

    @Override
    public void toHome(){
        createIcon.setImageResource(R.drawable.create_icon_inactive);
        teamIcon.setImageResource(R.drawable.team_icon_active);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_layout, new HomeFragment()).commit();
    }

    private void willView(){

        userIcon = (ImageView) findViewById(R.id.imageView_profileIcon);
        createIcon = (ImageView) findViewById(R.id.imageView_createIcon);
        teamIcon = (ImageView) findViewById(R.id.imageView_teamIcon);
        searchIcon = (ImageView) findViewById(R.id.imageView_searchIcon);
        final FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_layout, new HomeFragment()).commit();

        userIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userIcon.setImageResource(R.drawable.user_icon_active);
                createIcon.setImageResource(R.drawable.create_icon_inactive);
                teamIcon.setImageResource(R.drawable.team_icon_inactive2);
                searchIcon.setImageResource(R.drawable.search_icon_inactive);
                fragmentManager.beginTransaction().replace(R.id.fragment_layout, new ProfileFragment()).commit();
            }
        });

        createIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userIcon.setImageResource(R.drawable.user_icon_inactive);
                createIcon.setImageResource(R.drawable.create_icon_active);
                teamIcon.setImageResource(R.drawable.team_icon_inactive2);
                searchIcon.setImageResource(R.drawable.search_icon_inactive);
                fragmentManager.beginTransaction().replace(R.id.fragment_layout, new CreateTeamFragment()).commit();
            }
        });

        teamIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userIcon.setImageResource(R.drawable.user_icon_inactive);
                createIcon.setImageResource(R.drawable.create_icon_inactive);
                teamIcon.setImageResource(R.drawable.team_icon_active);
                searchIcon.setImageResource(R.drawable.search_icon_inactive);
                fragmentManager.beginTransaction().replace(R.id.fragment_layout, new HomeFragment()).commit();
            }
        });

        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userIcon.setImageResource(R.drawable.user_icon_inactive);
                createIcon.setImageResource(R.drawable.create_icon_inactive);
                teamIcon.setImageResource(R.drawable.team_icon_inactive2);
                searchIcon.setImageResource(R.drawable.search_icon_active);
                fragmentManager.beginTransaction().replace(R.id.fragment_layout, new SearchFragment()).commit();
            }
        });
    }

}

