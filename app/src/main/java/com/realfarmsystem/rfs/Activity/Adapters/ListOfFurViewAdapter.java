package com.realfarmsystem.rfs.Activity.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.realfarmsystem.rfs.Entity.Chinchilla;
import com.realfarmsystem.rfs.Entity.FurSkin;
import com.realfarmsystem.rfs.R;
import com.realfarmsystem.rfs.StaticData.FurSortingTypes;
import com.realfarmsystem.rfs.StaticData.TrackingStatuses;

import java.util.ArrayList;

public class ListOfFurViewAdapter extends BaseAdapter {
    private Context context;
    private Activity activity;
    private LayoutInflater layoutInflater;
    private ArrayList<FurSkin> furSkinArrayList;
    private ArrayList<FurSkin> mainListOfItem = new ArrayList<>();



    public ListOfFurViewAdapter(Context context, Activity activity, ArrayList<FurSkin> furSkinArrayList) {
        this.context = context;
        this.activity = activity;
        this.furSkinArrayList = furSkinArrayList;
        this.layoutInflater = LayoutInflater.from(context);
        mainListOfItem.addAll(furSkinArrayList);
    }

    class ViewHolder{
        TextView code, status, sorting, forSale, number;
    }



    @Override
    public int getCount() {
        return furSkinArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return furSkinArrayList.get(position);
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
            convertView = layoutInflater.inflate(R.layout.row_for_list_of_fur ,null);

            viewHolder.number = convertView.findViewById(R.id.numberListOfFurItem);
            viewHolder.code = convertView.findViewById(R.id.codeListOfFurItem);
            viewHolder.status = convertView.findViewById(R.id.statusListOfFurItem);
            viewHolder.sorting = convertView.findViewById(R.id.sortingListOfFurItem);
            viewHolder.forSale = convertView.findViewById(R.id.saleListOfFurItem);


            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }

        //initializing of item

        viewHolder.number.setText((position+1)+"");
        viewHolder.code.setText(furSkinArrayList.get(position).getCode());
        viewHolder.status.setText(TrackingStatuses.checkStatus(activity,furSkinArrayList.get(position).getStatus()));
        viewHolder.sorting.setText(FurSortingTypes.checkSortingType(activity,furSkinArrayList.get(position).getSorting()));
        Resources res = activity.getResources();
        if(furSkinArrayList.get(position).getIsForTanningAndSale()){
            viewHolder.forSale.setText(res.getString(R.string.forSale));
        }else{
            viewHolder.forSale.setText(res.getString(R.string.onlyTanning));
        }

        return convertView;
    }
    public void filter(String s){
        furSkinArrayList.clear();
        if(s.isEmpty()){
            furSkinArrayList.addAll(mainListOfItem);
        }else{
            for (FurSkin furSkin:mainListOfItem){
                if(furSkin.getCode().contains(s)){
                    furSkinArrayList.add(furSkin);
                }
            }
        }
        notifyDataSetChanged();
    }
}