package com.realfarmsystem.rfs.Activity.Fragments;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.realfarmsystem.rfs.Activity.Adapters.ListOfShelfViewAdapter;
import com.realfarmsystem.rfs.Entity.Shelf;
import com.realfarmsystem.rfs.JsonConvertation.JsonToArrayList;
import com.realfarmsystem.rfs.Network.GetRequest;
import com.realfarmsystem.rfs.R;
import com.realfarmsystem.rfs.StaticData.UrlData;
import cz.msebera.android.httpclient.Header;
import java.util.ArrayList;


public class FragmentListOfShelf extends Fragment {

    private View view;
    private SwipeMenuListView swipeMenuListView;
    private ArrayList<Shelf> shelfs;
    private ListOfShelfViewAdapter arrayAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list_of_shelf, container, false);
        swipeMenuListView = (SwipeMenuListView) view.findViewById(R.id.swipeListView);

        GetRequest getMyChin = new GetRequest(UrlData.getShelfs(), getActivity(), new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                System.out.println(new String(responseBody));
                JsonToArrayList<Shelf> converter = new JsonToArrayList<>();
                shelfs = converter.convert(responseBody,Shelf.class);
                createList();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(getContext(),"Error myChin",Toast.LENGTH_SHORT);
            }
        });
        getMyChin.commit();
        return view;
    }

    private void createList(){
        arrayAdapter = new ListOfShelfViewAdapter(getContext(),shelfs);
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
                editItem.setWidth(170);
                // set item title
                editItem.setTitle("Edit");
                // set item title fontsize
                editItem.setTitleSize(18);
                // set item title font color
                editItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(editItem);

                // create "delete" item
                SwipeMenuItem detailItem = new SwipeMenuItem(
                        getContext());
                // set item background
                detailItem.setBackground(new ColorDrawable(Color.rgb(0x00, 0xC9,
                        0xCE)));
                // set item width
                detailItem.setWidth(200);
                detailItem.setTitle("Details");
                detailItem.setTitleSize(18);
                // set item title font color
                detailItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(detailItem);
            }
        };

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
                        FragmentBuilderShelf builderShelf =  new FragmentBuilderShelf();
                        Bundle bundle = new Bundle();
                        bundle.putString("shelfId",shelfs.get(position).getId());
                        builderShelf.setArguments(bundle);

                        getActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .add(R.id.fragment_container,builderShelf, "builderShelf")
                                .addToBackStack(null)
                                .commit();

//                        Toast.makeText(getContext(),index + " a  sda",Toast.LENGTH_LONG).show();
                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });

    }

    public ListOfShelfViewAdapter getArrayAdapter() {
        return arrayAdapter;
    }
}
