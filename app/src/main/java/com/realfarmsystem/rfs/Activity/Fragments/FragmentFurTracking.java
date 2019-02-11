package com.realfarmsystem.rfs.Activity.Fragments;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.support.v4.app.Fragment;


import android.support.v7.widget.GridLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.realfarmsystem.rfs.Activity.Adapters.ListOfFurViewAdapter;
import com.realfarmsystem.rfs.Activity.DialogActivities.DialolgSorting;
import com.realfarmsystem.rfs.Entity.FurSkin;
import com.realfarmsystem.rfs.JsonConvertation.JsonToArrayList;
import com.realfarmsystem.rfs.Network.GetRequest;
import com.realfarmsystem.rfs.R;
import com.realfarmsystem.rfs.StaticData.UrlData;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;


public class FragmentFurTracking extends Fragment implements View.OnClickListener {

    private TableLayout tableLayout;
    private View view;
    private ProgressBar progressBar;
    private GridLayout gridLayout;
    private ArrayList<FurSkin> furSkins;
    private SwipeMenuListView swipeMenuListView;
    ListOfFurViewAdapter arrayAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list_of_fur, container, false);
        swipeMenuListView = (SwipeMenuListView) view.findViewById(R.id.swipeListView);

        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        getRequest();

        return view;
    }

    private void getRequest(){


        GetRequest getRequest = new GetRequest(UrlData.getMyFur(), this.getActivity(), new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                progressBar.setVisibility(View.GONE);
                System.out.println(new String(responseBody));
                JsonToArrayList<FurSkin> converter = new JsonToArrayList<>();
                furSkins = converter.convert(responseBody,FurSkin.class);
                System.out.println(furSkins.toString());
                createList(furSkins);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                System.out.println("error " + error.toString());
            }
        });
        getRequest.commit();
    }


    public void createList(ArrayList<FurSkin> furSkins){
        arrayAdapter = new ListOfFurViewAdapter(getContext(),getActivity(),furSkins);
        swipeMenuListView.setAdapter(arrayAdapter);
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem editItem = new SwipeMenuItem(
                        getContext());
                // set item background
                editItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                // set item width
                editItem.setWidth(200);
                // set item title
                editItem.setTitle("Archive");
                // set item title fontsize
                editItem.setTitleSize(15);
                // set item title font color
                editItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(editItem);

        }};

// set creator
        swipeMenuListView.setMenuCreator(creator);

        swipeMenuListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        Toast.makeText(getContext(),index + " a  sda",Toast.LENGTH_LONG).show();
                        break;
                    case 1:
                        Toast.makeText(getContext(),index + " a  sda",Toast.LENGTH_LONG).show();
                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });

    }



    @Override
    public void onClick(View v) {
        DialolgSorting dialolgSorting = new DialolgSorting();
        dialolgSorting.show(getFragmentManager(),"sorting dialog");
    }


    public ArrayList<FurSkin> getFurSkins() {
        return furSkins;
    }



    public ListOfFurViewAdapter getArrayAdapter() {
        return arrayAdapter;
    }
}
