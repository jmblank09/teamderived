package com.reginalddc.teamderapp.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.reginalddc.teamderapp.CreateTeamFragment.CreateTeamFragment;
import com.reginalddc.teamderapp.HomePageFragment.HomeFragment;
import com.reginalddc.teamderapp.Model.UserProfile;
import com.reginalddc.teamderapp.ProfileFragment.EditProfileFragment;
import com.reginalddc.teamderapp.ProfileFragment.ProfileFragment;
import com.reginalddc.teamderapp.R;
import com.reginalddc.teamderapp.SearchFragment.SearchFragment;

public class TeamActivity extends AppCompatActivity implements ProfileFragment.OnEditProfile, EditProfileFragment.UpdateProfile {

    ImageView userIcon, createIcon, teamIcon, searchIcon;
    UserProfile user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);
        willView();
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
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
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

