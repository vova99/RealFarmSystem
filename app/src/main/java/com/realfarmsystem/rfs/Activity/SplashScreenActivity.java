package com.realfarmsystem.rfs.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.realfarmsystem.rfs.Network.GenerationTokenRequest;
import com.realfarmsystem.rfs.R;

import cz.msebera.android.httpclient.Header;


public class SplashScreenActivity extends AppCompatActivity {
    private static int TIME_OUT = 2000;
    private static final String MY_SETTINGS = "mySettings";
    private boolean requestStatus = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(requestStatus){
                    Intent intent = new Intent(SplashScreenActivity.this,MainActivity.class);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(SplashScreenActivity.this, LogInActivity.class);
                    startActivity(intent);
                }
                finish();
            }
        },TIME_OUT);

        SharedPreferences sharedPreferences = getSharedPreferences(MY_SETTINGS,MODE_PRIVATE);
        if (!sharedPreferences.getString("hash","").isEmpty()){
            GenerationTokenRequest token = new GenerationTokenRequest();
            token.generationToken(this, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    requestStatus = true;
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                }
            });
        }

    }


}
