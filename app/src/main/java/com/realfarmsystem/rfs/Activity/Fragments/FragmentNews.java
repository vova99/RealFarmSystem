package com.realfarmsystem.rfs.Activity.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.realfarmsystem.rfs.Activity.Adapters.ListOfNewsAdapter;
import com.realfarmsystem.rfs.Activity.Adapters.TestAdapter;
import com.realfarmsystem.rfs.Entity.News;
import com.realfarmsystem.rfs.JsonConvertation.JsonToArrayList;
import com.realfarmsystem.rfs.Network.GetRequest;
import com.realfarmsystem.rfs.R;
import com.realfarmsystem.rfs.StaticData.UrlData;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class FragmentNews extends Fragment {

    private View v;
    private ArrayList<News> listOfNews = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private TestAdapter testAdapter;
    private ListView testListView;


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_news, container, false);

        GetRequest getNews = new GetRequest(UrlData.getNews(), getActivity(), new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                JsonToArrayList<News> converter = new JsonToArrayList<>();
                listOfNews = converter.convert(responseBody,News.class);
                System.out.println(listOfNews.size());
//                setRecyclerView();
                setListView();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
        getNews.commit();





        return v;
    }

    private void setRecyclerView(){
        recyclerView = v.findViewById(R.id.newsRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new ListOfNewsAdapter(listOfNews,getContext());
        recyclerView.setAdapter(adapter);
    }

    private void setListView(){
        testListView = v.findViewById(R.id.newsListView);
        testAdapter =  new TestAdapter(listOfNews,getContext());
        testListView.setAdapter(testAdapter);


    }
}
