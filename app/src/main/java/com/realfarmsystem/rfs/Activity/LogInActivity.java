package com.realfarmsystem.rfs.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.realfarmsystem.rfs.Network.GenerationTokenRequest;
import com.realfarmsystem.rfs.R;
import com.realfarmsystem.rfs.SystemProperty.SetLanguage;

import cz.msebera.android.httpclient.Header;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String MY_SETTINGS = "mySettings";
    private AutoCompleteTextView emailView;
    private EditText passwordView;
    private Button signInBtn;
    private String requestToken = "";
    private boolean checkOfPass = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SetLanguage.loadLocale(this);
        setContentView(R.layout.activity_login);
        Toast.makeText(this,"Log In",Toast.LENGTH_SHORT).show();
        signInBtn = findViewById(R.id.email_sign_in_button);
        signInBtn.setOnClickListener(this);
        emailView = (AutoCompleteTextView) findViewById(R.id.email);
        passwordView = findViewById(R.id.password);
        passwordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE){
                    signInBtn.callOnClick();
                    handled = true;
                }
                return handled;
            }
        });



        SharedPreferences sharedPreferences = getSharedPreferences(MY_SETTINGS,MODE_PRIVATE);
        if (!sharedPreferences.getString("hash","").isEmpty()){
            findViewById(R.id.login_form).setVisibility(View.GONE);
            findViewById(R.id.login_progress).setVisibility(View.VISIBLE);
            GenerationTokenRequest token = new GenerationTokenRequest();
            token.generationToken(this, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    returnToMainActivity();
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    findViewById(R.id.login_form).setVisibility(View.VISIBLE);
                    findViewById(R.id.login_progress).setVisibility(View.GONE);
                    Toast.makeText(LogInActivity.this,"error",Toast.LENGTH_SHORT).show();
                }
            });
        }



    }

    private boolean checkEmail(String email){
        if (email.contains("@")){
            return true;
        }
        return  false;
    }

    private boolean checkPassword(String email, final String password){
        findViewById(R.id.login_progress).setVisibility(View.VISIBLE);
        findViewById(R.id.login_form).setVisibility(View.GONE);
        GenerationTokenRequest token = new GenerationTokenRequest();
        token.generationToken(this, getApplicationContext(), email, password, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                requestToken = new String(responseBody);
                SharedPreferences sharedPreferences = getSharedPreferences(MY_SETTINGS, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("token",requestToken);
                editor.commit();
                returnToMainActivity();
            }


            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                findViewById(R.id.login_progress).setVisibility(View.GONE);
                findViewById(R.id.login_form).setVisibility(View.VISIBLE);
                System.out.println(statusCode+error.toString()+"");
                passwordView.setError(getString(R.string.error_incorrect_password));
            }
        });





        return checkOfPass;
    }

    private void returnToMainActivity(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    public void onClick(View v) {
        if (checkEmail(emailView.getText().toString())){
            System.out.println(emailView.getText().toString());
            checkPassword(emailView.getText().toString(),passwordView.getText().toString());
        }else{
            findViewById(R.id.login_progress).setVisibility(View.GONE);
            findViewById(R.id.login_form).setVisibility(View.VISIBLE);
            emailView.setError(getString(R.string.error_invalid_email));
        }
//        switch (v.getId()){
//            case R.id.email_sign_in_button:
//
//                break;
//        }
    }
}
