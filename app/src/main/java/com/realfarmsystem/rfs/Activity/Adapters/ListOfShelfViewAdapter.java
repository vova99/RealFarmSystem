package com.realfarmsystem.rfs.Activity.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;


import com.realfarmsystem.rfs.Entity.Shelf;
import com.realfarmsystem.rfs.R;

import java.util.ArrayList;

public class ListOfShelfViewAdapter extends BaseAdapter {
    private Context context;

    private LayoutInflater layoutInflater;
    private ArrayList<Shelf> shelfArrayList;
    private ArrayList<Shelf> mainListOfItem = new ArrayList<>();


    public ListOfShelfViewAdapter(Context context, ArrayList<Shelf> shelfArrayList) {
        this.context = context;
        this.shelfArrayList = shelfArrayList;
        this.layoutInflater = LayoutInflater.from(context);
        mainListOfItem.addAll(shelfArrayList);
    }


    class ViewHolder {
        TextView number, note;
    }


    @Override
    public int getCount() {
        return shelfArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return shelfArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.row_for_list_of_shelf, null);

            viewHolder.number = convertView.findViewById(R.id.numberListOfShelfItem);
            viewHolder.note = convertView.findViewById(R.id.noteListOfShelfItem);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

//        convertView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context, shelfArrayList.get(position).getId() + "", Toast.LENGTH_LONG);
//            }
//        });

        //initializing of item

        viewHolder.number.setText((position + 1) + "");
        viewHolder.note.setText(shelfArrayList.get(position).getNote());

        return convertView;
    }

    public void filter(String s) {
        shelfArrayList.clear();
        if (s.isEmpty()) {
            shelfArrayList.addAll(mainListOfItem);
        } else {
            for (Shelf shelf : mainListOfItem) {
                if (shelf.getNote()!=null && shelf.getNote().contains(s)) {
                    shelfArrayList.add(shelf);
                }
            }
        }
        notifyDataSetChanged();
    }
}