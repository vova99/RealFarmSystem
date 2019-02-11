package com.realfarmsystem.rfs.Network;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.realfarmsystem.rfs.StaticData.RequestStatus;

public class GetRequest {
    private static final String MY_SETTINGS = "mySettings";
    private RequestStatus requestStatus;
    private AsyncHttpResponseHandler responseHandler;
    private String url;
    private Activity activity;


    public GetRequest(String url, Activity activity,AsyncHttpResponseHandler responseHandler) {
        this.responseHandler = responseHandler;
        this.url = url;
        this.activity = activity;
    }

    public void commit(){
        SharedPreferences sharedPreferences = activity.getSharedPreferences(MY_SETTINGS, Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token","no token");
        System.out.println("token" +
                token);

        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Authorization ","Bearer "+token);
        client.get(url,responseHandler);
    }
}
