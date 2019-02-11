package com.realfarmsystem.rfs.Activity.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;


import com.loopj.android.http.AsyncHttpResponseHandler;
import com.realfarmsystem.rfs.Entity.Shelf;
import com.realfarmsystem.rfs.JsonConvertation.JsonToObj;
import com.realfarmsystem.rfs.Network.GetRequest;
import com.realfarmsystem.rfs.Network.PostRequest;
import com.realfarmsystem.rfs.R;
import com.realfarmsystem.rfs.StaticData.UrlData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


import cz.msebera.android.httpclient.Header;



public class FragmentAddFur extends Fragment {

    Button sendSkin;
    SharedPreferences sharedPreferences;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        System.out.println("create view");
        final View view = inflater.inflate(R.layout.fragment_add_fur, container, false);
        final EditText codeOfSkin = view.findViewById(R.id.editCodeOfSkin);
        final RadioGroup radioGroup = view.findViewById(R.id.radioGroup);
        sendSkin =(Button) view.findViewById(R.id.addFurButton);

        sendSkin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostRequest postRequest = new PostRequest(getActivity(),view.getContext());
                JSONObject params = new JSONObject();
                try {
                    params.put("code", codeOfSkin.getText());
                    if (radioGroup.getCheckedRadioButtonId() == R.id.radioButtonTanningAndSale) {
                        params.put("isForTanningAndSale", true);
                    } else
                    {
                        params.put("isForTanningAndSale", false);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JSONArray jsonArray = new JSONArray();
                jsonArray.put(params);


                try {
                    postRequest.post(jsonArray, UrlData.getAddFur(), new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            codeOfSkin.setText("");
                            Toast.makeText(getContext(),"Success",Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                            Toast.makeText(getContext(),new String(responseBody),Toast.LENGTH_LONG).show();
                            System.out.println("post error  " + error.toString()+"\n"+new String(responseBody));
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });




        return view;
    }


}
