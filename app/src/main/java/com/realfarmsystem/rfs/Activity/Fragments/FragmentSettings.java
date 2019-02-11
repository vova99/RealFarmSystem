package com.realfarmsystem.rfs.Activity.Fragments;


import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.realfarmsystem.rfs.Activity.DialogActivities.DialogSettingsChangePassword;
import com.realfarmsystem.rfs.Activity.MainActivity;
import com.realfarmsystem.rfs.R;
import com.realfarmsystem.rfs.SystemProperty.SetLanguage;

import java.util.ArrayList;
import java.util.Locale;


public class FragmentSettings extends Fragment {

    private View view;
    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_settings, container, false);
        listView = view.findViewById(R.id.list_of_settings);

        String[] items = {"Change language","Change password","Upload photo"};



        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,items);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        showChangeLanguageDialog();
                        break;
                    case 1:
                        DialogSettingsChangePassword changePassword = new DialogSettingsChangePassword(getActivity(),getContext());
                        changePassword.show(getFragmentManager(),"changePassword");
                        break;

                }
            }
        });

        return view;
    }


    private void showChangeLanguageDialog(){
        final String[] listLanguages = {"English","Українська","Русский"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Choose language...");
        builder.setSingleChoiceItems(listLanguages, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:
                        SetLanguage.setLocale("en",getActivity());
                        getActivity().recreate();
                        break;
                    case 1:
                        SetLanguage.setLocale("uk",getActivity());
                        getActivity().recreate();
                        break;
                    case 2:
                        SetLanguage.setLocale("ru",getActivity());
                        getActivity().recreate();
                        break;
                }
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


}
