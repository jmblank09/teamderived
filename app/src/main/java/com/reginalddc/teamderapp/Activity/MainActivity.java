package com.reginalddc.teamderapp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.reginalddc.teamderapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread loadingThread = new Thread() {

            @Override
            public void run() {
                try {
                    super.run();
                    sleep(4000);  //Delay of 10 seconds
                } catch (Exception e) {

                } finally {
                    Intent intent = new Intent(MainActivity.this,
                            LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        loadingThread.start();
    }
}



