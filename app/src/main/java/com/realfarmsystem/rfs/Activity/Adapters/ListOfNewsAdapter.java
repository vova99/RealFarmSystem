package com.realfarmsystem.rfs.Activity.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.realfarmsystem.rfs.Entity.News;
import com.realfarmsystem.rfs.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ListOfNewsAdapter extends RecyclerView.Adapter<ListOfNewsAdapter.ViewHolder> {

    private ArrayList<News> listOfNews;
    private Context context;

    public ListOfNewsAdapter(ArrayList<News> listOfNews, Context context) {
        this.listOfNews = listOfNews;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_for_news,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        News news = listOfNews.get(i);
        String title = news.getTitle();
        if (title.length()>56){
            title = title.substring(0,52)+"...";
        }
        viewHolder.title.setText(title);

        String desc = news.getDescription();
        if (desc.length()>200){
            desc = desc.substring(0,196)+"...";
        }
        viewHolder.description.setText(desc);
    }

    @Override
    public int getItemCount() {
        return listOfNews.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView title;
        public TextView description;
        public ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.newsTitle);
            description = itemView.findViewById(R.id.newsDesc);
            image = itemView.findViewById(R.id.newsImage);

        }
    }
}
