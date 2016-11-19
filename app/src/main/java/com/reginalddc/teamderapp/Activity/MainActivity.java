package com.reginalddc.teamderapp.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.reginalddc.teamderapp.Model.UserProfile;
import com.reginalddc.teamderapp.R;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    EditText username, password;
    ProgressDialog prgDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.editText_username);
        password = (EditText) findViewById(R.id.editText_password);
        prgDialog = new ProgressDialog(this);
        prgDialog.setTitle("Connecting");
        prgDialog.setMessage("Please wait...");
        prgDialog.setCancelable(false);


    }

    public void registerAccount(View v){
        Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
    }

    public void loginAccount(View v){
        String email = username.getText().toString();
        String pass = password.getText().toString();

        RequestParams params = new RequestParams();
        params.put("email", email);
        params.put("password", pass);
        invokeWS(params);
    }

    public void invokeWS(RequestParams params){
        //to show the Progress Dialog for Connecting and Dismissed it after it loads the next page
        prgDialog.show();
        Runnable progressRunnable = new Runnable() {
            @Override
            public void run() {
                prgDialog.cancel();
            }
        };
        Handler pdCanceller = new Handler();
        pdCanceller.postDelayed(progressRunnable, 1000);
        AsyncHttpClient client = new AsyncHttpClient();

        client.get("http://107.170.61.180/android/teamderived_api/auth/login.php", params, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(String response){
                try{

                    JSONObject obj = new JSONObject(response);
                    if (obj.getBoolean("success")){
                        UserProfile user = new UserProfile();
                        user.setUserID(obj.getInt("user_id"));
                        Toast.makeText(getApplicationContext(), "Success Login!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), TeamActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Wrong Username or Password!", Toast.LENGTH_LONG).show();
                    }


                }catch(Exception e){
                    Toast.makeText(getApplicationContext(), "Error Occured!", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });
    }


}
