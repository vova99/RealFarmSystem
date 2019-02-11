package com.realfarmsystem.rfs.Network;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.JsonObject;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.realfarmsystem.rfs.StaticData.UrlData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import cz.msebera.android.httpclient.entity.StringEntity;

public class PostRequest {
    private static final String MY_SETTINGS = "mySettings";
    private Activity activity;
    private Context context;


    public PostRequest(Activity activity, Context context) {
        this.activity = activity;
        this.context = context;
    }

    public  void post(JSONArray jsonArray, String url, AsyncHttpResponseHandler responseHandler) throws JSONException, IOException {
        SharedPreferences sharedPreferences = activity.getSharedPreferences(MY_SETTINGS, Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token","no token");

//        JSONObject params = new JSONObject();
//        params.put("code",11234);
//        params.put("isForTanningAndSale",true);
//
//        JSONArray jsonArray = new JSONArray();
//        jsonArray.put(params);

        StringEntity entity = new StringEntity(jsonArray.toString());
        entity.setContentType("application/json");

        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Authorization ","Bearer "+token);

        client.post(context,url,entity,"application/json",responseHandler);
    }
}
