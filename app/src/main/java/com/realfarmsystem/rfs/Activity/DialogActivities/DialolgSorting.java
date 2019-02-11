package com.realfarmsystem.rfs.Activity.DialogActivities;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.realfarmsystem.rfs.R;
import com.realfarmsystem.rfs.StaticData.FurSortingTypes;
import com.realfarmsystem.rfs.StaticData.TrackingStatuses;

import java.util.ArrayList;
import java.util.List;


@SuppressLint("ValidFragment")
public class DialolgSorting extends AppCompatDialogFragment {

    private View view;
    private FragmentDialogSortingListener listener;



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        view = layoutInflater.inflate(R.layout.fragment_dialog_sorting,null);

        builder.setView(view)
                .setTitle("Select items")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).
                setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.arrayOfItemsSelected(getListOfStatuses(),getListOfSorting(),getListOfSale());
                    }
                });

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (FragmentDialogSortingListener) context;
    }



    private ArrayList<Integer> getListOfStatuses(){
        ArrayList<Integer> idOfStatuses = new ArrayList<>();
        ArrayList<CheckBox> status_checkBoxes = new ArrayList<>();


        status_checkBoxes.add((CheckBox) view.findViewById(R.id.checkbox_waiting));
        status_checkBoxes.add((CheckBox) view.findViewById(R.id.checkbox_inProccess));
        status_checkBoxes.add((CheckBox) view.findViewById(R.id.checkbox_sorted));
        status_checkBoxes.add((CheckBox) view.findViewById(R.id.checkbox_sold));
        for (CheckBox checkBox:status_checkBoxes) {
            if (checkBox.isChecked()){
                idOfStatuses.add(TrackingStatuses.getStatusId(getActivity(),checkBox.getText().toString()));
            }
        }
        return idOfStatuses;
    }


    private ArrayList<Integer> getListOfSorting(){
        ArrayList<Integer> idOfSorting = new ArrayList<>();
        ArrayList<CheckBox> statu_checkBoxes = new ArrayList<>();


        ArrayList<CheckBox> sorting_checkBoxes = new ArrayList<>();
        sorting_checkBoxes.add((CheckBox) view.findViewById(R.id.checkbox_none));
        sorting_checkBoxes.add((CheckBox) view.findViewById(R.id.checkbox_select));
        sorting_checkBoxes.add((CheckBox) view.findViewById(R.id.checkbox_firstClassPlus));
        sorting_checkBoxes.add((CheckBox) view.findViewById(R.id.checkbox_firstClass));
        sorting_checkBoxes.add((CheckBox) view.findViewById(R.id.checkbox_secondClass));
        sorting_checkBoxes.add((CheckBox) view.findViewById(R.id.checkbox_mix));

        for (CheckBox checkBox:sorting_checkBoxes) {
            if (checkBox.isChecked()){
                idOfSorting.add(FurSortingTypes.getSortingTypeId(getActivity(),checkBox.getText().toString()));
            }
        }

        return idOfSorting;
    }

    private ArrayList<Boolean> getListOfSale(){
        ArrayList<Boolean> idOfSale = new ArrayList<>();

        ArrayList<CheckBox> forSale_checkBoxes = new ArrayList<>();
        forSale_checkBoxes.add((CheckBox) view.findViewById(R.id.checkbox_forSale_no));
        forSale_checkBoxes.add((CheckBox) view.findViewById(R.id.checkbox_forSale_yes));

        if(((CheckBox) view.findViewById(R.id.checkbox_forSale_yes)).isChecked()){
           idOfSale.add(true);
        }

        if(((CheckBox) view.findViewById(R.id.checkbox_forSale_no)).isChecked()){
            idOfSale.add(false);
        }

        return idOfSale;
    }

    public interface FragmentDialogSortingListener{
        void arrayOfItemsSelected(ArrayList<Integer> statuses, ArrayList<Integer> sorting, ArrayList<Boolean> forSale);
    }

}
