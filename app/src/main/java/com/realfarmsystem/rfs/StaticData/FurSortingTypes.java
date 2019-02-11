package com.realfarmsystem.rfs.StaticData;

import android.app.Activity;
import android.content.res.Resources;

import com.realfarmsystem.rfs.R;

public class FurSortingTypes {

    public static String checkSortingType(Activity activity, int id){
        Resources res = activity.getResources();
        switch (id){
            case 0:
                return res.getString(R.string.none);
            case 1:
                return res.getString(R.string.select);
            case 2:
                return res.getString(R.string.firstClassPlus);
            case 3:
                return res.getString(R.string.firstClass);
            case 4:
                return res.getString(R.string.secondClass);
            case 5:
                return res.getString(R.string.mix);
        }
        return "null";
    }

    public static int getSortingTypeId(Activity activity,String str){
        Resources res = activity.getResources();
        str.equals(res.getString(R.string.mix));

        if(str.equals(res.getString(R.string.none))){
            return 0;
        }if(str.equals(res.getString(R.string.select))){
            return 1;
        }if(str.equals(res.getString(R.string.firstClassPlus))){
            return 2;
        }if(str.equals(res.getString(R.string.firstClass))){
            return 3;
        }if(str.equals(res.getString(R.string.secondClass))){
            return 4;
        }if(str.equals(res.getString(R.string.mix))){
            return 5;
        }

        return 0;

    }
}
