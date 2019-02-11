package com.realfarmsystem.rfs.SystemProperty;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;

import java.util.Locale;

public class SetLanguage {
    private static final String MY_SETTINGS = "mySettings";
    public static void setLocale(String lang, Activity activity){
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);

        Configuration configuration = new Configuration();
        configuration.locale = locale;
        activity.getBaseContext().getResources().updateConfiguration(configuration,
                activity.getBaseContext().getResources().getDisplayMetrics());


        SharedPreferences.Editor editor = activity.getSharedPreferences(MY_SETTINGS, Context.MODE_PRIVATE).edit();
        editor.putString("My_Lang",lang);
        editor.apply();
    }

    public static void loadLocale(Activity activity){
        SharedPreferences pref = activity.getSharedPreferences(MY_SETTINGS,Context.MODE_PRIVATE);
        String lang = pref.getString("My_Lang","");
        setLocale(lang,activity);
    }
}
