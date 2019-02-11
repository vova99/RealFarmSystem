package com.realfarmsystem.rfs.Network;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;

import cz.msebera.android.httpclient.entity.StringEntity;

public class PutRequest {
    private static final String MY_SETTINGS = "mySettings";
    private Activity activity;
    private Context context;

    public PutRequest(Activity activity, Context context) {
        this.activity = activity;
        this.context = context;
    }

    public  void put(String url, AsyncHttpResponseHandler responseHandler) throws JSONException, IOException {
        SharedPreferences sharedPreferences = activity.getSharedPreferences(MY_SETTINGS, Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token","no token");

//
//        StringEntity entity = new StringEntity(jsonArray.toString());
//        entity.setContentType("application/json");

        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Authorization ","Bearer "+token);
        client.put(url,responseHandler);
    }
}
