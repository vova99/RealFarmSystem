package com.realfarmsystem.rfs.Activity.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.realfarmsystem.rfs.Entity.Chinchilla;
import com.realfarmsystem.rfs.R;
import com.realfarmsystem.rfs.StaticData.ChinchillaColors;

import java.util.ArrayList;
import java.util.List;

public class ListOfChinViewAdapter extends BaseAdapter{
    private Context context;
    private Activity activity;
    private LayoutInflater layoutInflater;
    private ArrayList<Chinchilla> chinchillaArrayList;
    private ArrayList<Chinchilla> mainListOfItem = new ArrayList<>();


    public ListOfChinViewAdapter(Activity activity,Context context, ArrayList<Chinchilla> chinchillaArrayList) {
        this.activity = activity;
        this.context = context;
        this.chinchillaArrayList = chinchillaArrayList;
        this.layoutInflater = LayoutInflater.from(context);
        this.mainListOfItem.addAll(chinchillaArrayList);
    }

    class ViewHolder{
        TextView code, color, location;
        ImageView image;
    }



    @Override
    public int getCount() {
        return chinchillaArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return chinchillaArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.row_for_list_of_chin,null);

            viewHolder.code = convertView.findViewById(R.id.codeListOfChinItem);
            viewHolder.color = convertView.findViewById(R.id.colorListOfChinItem);
            viewHolder.location = convertView.findViewById(R.id.locationListOfChinItem);
            viewHolder.image = convertView.findViewById(R.id.imageListOfChinItem);

            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }

        //initializing of item
        viewHolder.code.setText(chinchillaArrayList.get(position).getCode());
        viewHolder.color.setText(ChinchillaColors.checkColor(activity,chinchillaArrayList.get(position).getColor()));
        viewHolder.location.setText(chinchillaArrayList.get(position).getCageId());
        if (chinchillaArrayList.get(position).getIsMale()){
            viewHolder.image.setImageResource(R.drawable.ic_male);
        }else {
            viewHolder.image.setImageResource(R.drawable.ic_female);
        }

        return convertView;
    }
    public void filter(String s){
        chinchillaArrayList.clear();
        if(s.isEmpty()){
            chinchillaArrayList.addAll(mainListOfItem);
        }else{
            for (Chinchilla ch:mainListOfItem){
                if(ch.getCode().contains(s)){
                    chinchillaArrayList.add(ch);
                }
            }
        }
        notifyDataSetChanged();
    }
}
