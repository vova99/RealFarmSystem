package com.realfarmsystem.rfs.StaticData;

import android.app.Activity;
import android.content.res.Resources;

import com.realfarmsystem.rfs.R;



public class ChinchillaColors {
    public static String checkColor(Activity activity, int id){
        Resources res = activity.getResources();
        switch (id){
            case 1:
                return res.getString(R.string.standart);
            case 2:
                return res.getString(R.string.biege);
            case 3:
                return res.getString(R.string.blackVelvet);
            case 4:
                return res.getString(R.string.whiteWilson);
            case 5:
                return res.getString(R.string.ebony);
            case 6:
                return res.getString(R.string.violet);
            case 7:
                return res.getString(R.string.blue);
            case 8:
                return res.getString(R.string.saphire);
            case 9:
                return res.getString(R.string.blackPearl);
        }
        return "";
    }
}
