package com.realfarmsystem.rfs.StaticData;

import android.app.Activity;
import android.content.res.Resources;

import com.realfarmsystem.rfs.R;



public class TrackingStatuses {

    public static String checkStatus(Activity activity,int id){
        Resources res = activity.getResources();
        switch (id){
            case 2: return res.getString(R.string.waiting);

            case 4: return res.getString(R.string.inProccess);

            case 6: return res.getString(R.string.sorted);

            case 8: return res.getString(R.string.sold);
        }
        return "null";
    }

    public static int getStatusId(Activity activity,String str){
        Resources res = activity.getResources();

        if(str.equals(res.getString(R.string.waiting))){
            return 2;
        }if(str.equals(res.getString(R.string.inProccess))){
            return 4;
        }if(str.equals(res.getString(R.string.sorted))){
            return 6;
        }if(str.equals(res.getString(R.string.sold))){
            return 8;
        }

        return 2;
    }
}
