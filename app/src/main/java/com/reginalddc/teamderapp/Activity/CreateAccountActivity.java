package com.reginalddc.teamderapp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.reginalddc.teamderapp.R;

import org.json.JSONObject;

public class CreateAccountActivity extends AppCompatActivity {

    TextView login;
    EditText username, email, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        username = (EditText) findViewById(R.id.editText_username);
        email = (EditText) findViewById(R.id.editText_email);
        password = (EditText) findViewById(R.id.editText_password);
        willView();
    }

    private void willView(){
        login = (TextView) findViewById(R.id.textView_Login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateAccountActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void createAccount(View v){
        String mail = email.getText().toString();
        String name = username.getText().toString();
        String pass = password.getText().toString();

        RequestParams params = new RequestParams();
        params.put("name", name);
        params.put("email", mail);
        params.put("password", pass);
        invokeWS(params);
    }

    public void invokeWS(RequestParams params){
        AsyncHttpClient client = new AsyncHttpClient();

        client.get("http://107.170.61.180/android/teamderived_api/users/register.php", params, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(String response){
                try{

                    JSONObject obj = new JSONObject(response);
                    if (obj.getBoolean("success")){
                        Toast.makeText(getApplicationContext(), "Account has been registered!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Kindly Complete the Fields", Toast.LENGTH_LONG).show();
                    }


                }catch(Exception e){
                    Toast.makeText(getApplicationContext(), "Error Occured!", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });
    }
}
