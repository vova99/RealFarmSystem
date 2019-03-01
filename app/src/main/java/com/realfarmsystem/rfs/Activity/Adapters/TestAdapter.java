package com.realfarmsystem.rfs.Activity.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.realfarmsystem.rfs.Entity.News;
import com.realfarmsystem.rfs.R;

import java.util.ArrayList;

public class TestAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private ArrayList<News> listOfNews;
    private Context context;

    public TestAdapter(ArrayList<News> listOfNews, Context context) {
        this.listOfNews = listOfNews;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listOfNews.size();
    }

    @Override
    public Object getItem(int position) {
        return listOfNews.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TestAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new TestAdapter.ViewHolder();
            convertView = layoutInflater.inflate(R.layout.test_row_of_news, null);

            viewHolder.title = convertView.findViewById(R.id.newsTitle);
            viewHolder.description = convertView.findViewById(R.id.newsDesc);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (TestAdapter.ViewHolder) convertView.getTag();
        }

        String title = listOfNews.get(position).getTitle();
        if (title.length()>50){
            title = title.substring(0,46)+"...";
        }

        String desc = listOfNews.get(position).getDescription();
        if (desc.length()>100){
            desc = desc.substring(0,96)+"...";
        }
        viewHolder.title.setText(title);
        viewHolder.description.setText(desc);


        return convertView;
    }

    public  class  ViewHolder{
        public TextView title;
        public TextView description;
        public ImageView image;
    }
}
