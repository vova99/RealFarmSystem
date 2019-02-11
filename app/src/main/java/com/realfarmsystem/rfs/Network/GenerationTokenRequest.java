package com.realfarmsystem.rfs.Network;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;
import com.realfarmsystem.rfs.StaticData.UrlData;
import com.realfarmsystem.rfs.StaticData.RequestStatus;


import cz.msebera.android.httpclient.Header;

public class GenerationTokenRequest {
    private static final String MY_SETTINGS = "mySettings";
    private RequestStatus status;
    private String token;

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }



    public void generationToken(Activity activity,Context context,String login, String password, AsyncHttpResponseHandler responseHandler){
        String hash ="Basic "+ Base64.encodeToString((login+":"+password).getBytes(),Base64.DEFAULT);

        SharedPreferences sharedPreferences = activity.getSharedPreferences(MY_SETTINGS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("hash",hash);
        editor.commit();

        AsyncHttpClient syncHttpClient = new AsyncHttpClient();
        syncHttpClient.addHeader("Authorization",hash);
        syncHttpClient.post(UrlData.getAuth(), responseHandler);
    }

    public void generationToken(Activity activity, AsyncHttpResponseHandler responseHandler){
        SharedPreferences sharedPreferences = activity.getSharedPreferences(MY_SETTINGS, Context.MODE_PRIVATE);
        String hash = sharedPreferences.getString("hash","");

        AsyncHttpClient  syncHttpClient = new AsyncHttpClient();
        syncHttpClient.addHeader("Authorization",hash);
        syncHttpClient.post(UrlData.getAuth(),responseHandler);
    }

    private class TokenRun extends Thread{
        AsyncHttpResponseHandler responseHandler;
        String hash;

        public TokenRun(AsyncHttpResponseHandler responseHandler, String hash) {
            this.responseHandler = responseHandler;
            this.hash = hash;
        }

        @Override
        public void run() {

        }
    }
}
