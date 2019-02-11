package com.realfarmsystem.rfs.Activity.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.realfarmsystem.rfs.Entity.User;
import com.realfarmsystem.rfs.JsonConvertation.JsonToObj;
import com.realfarmsystem.rfs.Network.GetRequest;
import com.realfarmsystem.rfs.R;
import com.realfarmsystem.rfs.StaticData.UrlData;

import cz.msebera.android.httpclient.Header;

public class FragmentProfile extends Fragment {

    private View view;
    private User user;
    private Button buttonSave;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        buttonSave = view.findViewById(R.id.buttonSave);
        GetRequest userInfo = new GetRequest( UrlData.getUserInfo(),getActivity(), new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                JsonToObj<User> converter = new JsonToObj<>();
                user = converter.convert(responseBody,User.class);
                setText();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                System.out.println("error user");
            }
        });
        userInfo.commit();

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }

    private void updateUsserData(){
        EditText email = view.findViewById(R.id.changeEmail);
        EditText phone = view.findViewById(R.id.changePhone);
        EditText city = view.findViewById(R.id.changeCity);
        EditText country = view.findViewById(R.id.changeCountry);

        user.setEmail(email.getText().toString());
        user.setPhone(email.getText().toString());


    }


    private void setText(){
        TextView name = view.findViewById(R.id.name_profile);
        name.setText(user.getName());
        TextView surName = view.findViewById(R.id.surName_profile);
        surName.setText(user.getSurname());
        TextView farmCode = view.findViewById(R.id.farmCode_profile);
        farmCode.setText(user.getFarmCode());
        EditText email = view.findViewById(R.id.changeEmail);
        email.setText(user.getEmail());
    }

}

