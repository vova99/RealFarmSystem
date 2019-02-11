package com.realfarmsystem.rfs.Activity.Fragments;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.realfarmsystem.rfs.Entity.Cage;
import com.realfarmsystem.rfs.Entity.Chinchilla;
import com.realfarmsystem.rfs.Entity.Shelf;
import com.realfarmsystem.rfs.JsonConvertation.JsonToObj;
import com.realfarmsystem.rfs.Network.GetRequest;
import com.realfarmsystem.rfs.R;
import com.realfarmsystem.rfs.StaticData.ChinchillaColors;
import com.realfarmsystem.rfs.StaticData.UrlData;

import java.nio.file.Path;

import cz.msebera.android.httpclient.Header;


public class FragmentBuilderShelf extends Fragment {

    private View view;
    private Shelf shelf;
    private LinearLayout chinDetailsLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_builder_shelf, container, false);
        chinDetailsLayout = view.findViewById(R.id.chinDetails);
        chinDetailsLayout.setVisibility(View.INVISIBLE);


//        String shelfId = "5ba0c75e-0e00-451b-b6bc-20995b6d2089";

        Bundle bundle = getArguments();

        String shelfId = bundle.getString("shelfId");
        if (shelfId!=null) {
            GetRequest builderRequest = new GetRequest(UrlData.getShelfsFullInfo() + shelfId, getActivity(), new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    JsonToObj<Shelf> converter = new JsonToObj<>();
                    shelf = converter.convert(responseBody, Shelf.class);
                    System.out.println(shelf.toString());
                    buildShelf();
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                }
            });

            builderRequest.commit();
        }else {
            Toast.makeText(getContext(),"Null object shelf",Toast.LENGTH_SHORT).show();
        }


        return view;
    }


    private void buildShelf(){
        TableLayout table = (TableLayout) view.findViewById(R.id.shelfTable);

        int margin = getPixelValue(getContext(),1);

        TableRow.LayoutParams maleParams = new TableRow.LayoutParams();
        maleParams.width = getPixelValue(getContext(),57);
        maleParams.height = getPixelValue(getContext(),57);
        maleParams.gravity = Gravity.CENTER_VERTICAL;
//        maleParams.setMargins(margin,margin,margin,margin);

        TableRow.LayoutParams femaleParams = new TableRow.LayoutParams();
        femaleParams.width = getPixelValue(getContext(),63);
        femaleParams.height = getPixelValue(getContext(),63);
//        femaleParams.gravity = Gravity.CENTER;
//        femaleParams.setMargins(margin,margin,margin,margin);

        for (int i=0; i < shelf.getRowsOrderedByFlor().size();i++){
            TableRow row = new TableRow(getContext());

            ImageButton male = new ImageButton(getContext());
            male.setImageResource(R.drawable.ic_male);
            male.setLayoutParams(maleParams);
            male.setScaleType(ImageView.ScaleType.CENTER_CROP);
            male.setOnClickListener(onClickListener);
//            male.setBackgroundResource(R.color.colorBackgroundTable);
            if (shelf.getRowsOrderedByFlor().get(i).getMale()!=null){
                male.setTag(shelf.getRowsOrderedByFlor().get(i).getMale());
            }else {
                male.setVisibility(View.INVISIBLE);
            }
            row.addView(male);

            for (Cage cage:shelf.getRowsOrderedByFlor().get(i).getCages()){
                ImageButton cageBtn = new ImageButton(getContext());
                cageBtn.setLayoutParams(femaleParams);
                cageBtn.setOnClickListener(onClickListener);
                if (cage.getChinchillas().size()==0){
//                    cageBtn.setVisibility(View.INVISIBLE);
                }else {
                    cageBtn.setTag(cage.getChinchillas().get(0));
                    cageBtn.setImageResource(R.drawable.ic_female);
                    cageBtn.setScaleType(ImageView.ScaleType.CENTER_CROP);
//                    cageBtn.setBackgroundResource(R.color.colorWhite);
                }

                row.addView(cageBtn);
            }

            table.addView(row,0);
        }


    }


    public static int getPixelValue(Context context, int dimenId) {
        Resources resources = context.getResources();

        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dimenId,
                resources.getDisplayMetrics()
        );
    }

    private View.OnClickListener  onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Chinchilla ch =(Chinchilla) v.getTag();
            if (ch!=null){
                Toast.makeText(getContext(),ch.getCode(),Toast.LENGTH_LONG).show();
                setChinDetails(ch);
            }else{
                if (chinDetailsLayout.getVisibility()==View.VISIBLE){
                    chinDetailsLayout.setVisibility(View.INVISIBLE);
                }
                Toast.makeText(getContext(),"Null object",Toast.LENGTH_LONG).show();
            }
        }
    };

    private void setChinDetails(Chinchilla ch){
        chinDetailsLayout.setVisibility(View.VISIBLE);

        ImageView imageView = view.findViewById(R.id.imageOfChinDetails);
        if (ch.getIsMale()){
            imageView.setImageResource(R.drawable.ic_male);
        }else {
            imageView.setImageResource(R.drawable.ic_female);
        }

        TextView codeOfChin = view.findViewById(R.id.codeOfChinDetails);
        codeOfChin.setText(ch.getCode());

        TextView isInFamily = view.findViewById(R.id.isInFamily);
        isInFamily.setText(ch.getIsInFamily().toString());

        TextView color = view.findViewById(R.id.colorOfChin);
        color.setText(ChinchillaColors.checkColor(getActivity(),ch.getColor()));

//        TextView existenceOfChin = view.findViewById(R.id.existenceOfChin);
//        existenceOfChin.setText(ch.getAimOfExistence());


    }

}
