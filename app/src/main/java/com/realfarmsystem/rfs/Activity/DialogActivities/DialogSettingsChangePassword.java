package com.realfarmsystem.rfs.Activity.DialogActivities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.realfarmsystem.rfs.Entity.User;
import com.realfarmsystem.rfs.JsonConvertation.JsonToObj;
import com.realfarmsystem.rfs.Network.GetRequest;
import com.realfarmsystem.rfs.Network.PutRequest;
import com.realfarmsystem.rfs.R;
import com.realfarmsystem.rfs.StaticData.UrlData;

import org.json.JSONException;

import java.io.IOException;

import cz.msebera.android.httpclient.Header;

@SuppressLint("ValidFragment")
public class DialogSettingsChangePassword extends AppCompatDialogFragment {
    private Activity activity;
    private Context context;
    private View view;
    private User userInfo;
//    private FragmentDialogSortingListener listener;


    public DialogSettingsChangePassword(Activity activity, Context context) {
        this.activity = activity;
        this.context = context;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        view = layoutInflater.inflate(R.layout.fragment_dialog_change_password,null);

        final EditText oldPassword = view.findViewById(R.id.oldPassword);
        final EditText newPassword = view.findViewById(R.id.newPassword);

        builder.setView(view)
                .setTitle("Select items")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).
                setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        GetRequest userInfo = new GetRequest( UrlData.getUserInfo(),getActivity(), new AsyncHttpResponseHandler() {
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                                JsonToObj<User> user = new JsonToObj<>();
                                User userInfo = user.convert(responseBody,User.class);
                                setUserInfo(userInfo);
                                try {
                                    changePassword(userInfo.getId(),oldPassword.getText().toString(),
                                            newPassword.getText().toString());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                                System.out.println("error user");
                            }
                        });

                        userInfo.commit();
                    }
                });

        return builder.create();
    }

    private void changePassword(String id,String oldPass, String newPass) throws IOException, JSONException {


        PutRequest putRequest = new PutRequest(activity, context);
        System.out.println(UrlData.getUserUpdatePassword()+id+"/"+oldPass+"/"+newPass);
        putRequest.put(UrlData.getUserUpdatePassword()+id+"/"+oldPass+"/"+newPass, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Toast.makeText(context,"succseeeee",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(context,new String(responseBody),Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void setUserInfo(User userInfo) {
        this.userInfo = userInfo;
    }

}
