package com.realfarmsystem.rfs.Activity.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.realfarmsystem.rfs.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentChinDetails extends Fragment {

    private View view;

    public FragmentChinDetails() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chin_details, container, false);
        Bundle bundle = getArguments();

//        String shelfId = bundle.

        return view;
    }

}
