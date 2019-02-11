package com.realfarmsystem.rfs.StaticData;

import android.app.Activity;
import android.content.res.Resources;

import com.realfarmsystem.rfs.R;

public class Countries {

    public static String getNameOfCountryById(Activity activity,int id){
        Resources res = activity.getResources();

        switch (id){
            case 1:
                return res.getString(R.string.country_uk);
            case 2:
                return res.getString(R.string.country_lv);
            case 3:
                return res.getString(R.string.country_lt);
            case 4:
                return res.getString(R.string.country_md);
            case 5:
                return res.getString(R.string.country_pl);
            case 6:
                return res.getString(R.string.country_ro);
            case 7:
                return res.getString(R.string.country_rs);
        }

        return res.getString(R.string.none);
    }

}
