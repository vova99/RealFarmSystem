package com.realfarmsystem.rfs.Network;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.realfarmsystem.rfs.SystemProperty.SetLanguage;

public class FailResponseServer {
    public static void checkResponse(final Activity activity, Context context, int status, byte[] responseBody){
        switch (status){
            case 401:
                GenerationTokenRequest tokenRequest = new GenerationTokenRequest();
//                tokenRequest.generationToken(activity);
                activity.recreate();
                break;
            case 400:
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Bad request");
                builder.setMessage(new String(responseBody));
                builder.setPositiveButton("Restart", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        activity.recreate();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();

        }
    }
}
