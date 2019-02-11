package com.realfarmsystem.rfs.Activity.Fragments;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableRow;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.DefaultValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.realfarmsystem.rfs.Entity.FurStatisticPerCountry;
import com.realfarmsystem.rfs.Entity.SortsStatistics;
import com.realfarmsystem.rfs.JsonConvertation.JsonToArrayList;
import com.realfarmsystem.rfs.Network.GetRequest;
import com.realfarmsystem.rfs.R;
import com.realfarmsystem.rfs.StaticData.FurSortingTypes;
import com.realfarmsystem.rfs.StaticData.UrlData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import cz.msebera.android.httpclient.Header;


public class FragmentDashboard extends Fragment {


    private View view;
    private PieChart pieChart;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        pieChart = view.findViewById(R.id.fragment_dashboard_pieChart);
        LinearLayout linearLayout = view.findViewById(R.id.chart_container);

        GetRequest getStatistic = new GetRequest(UrlData.getSortingsAllocation(), getActivity(), new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                System.out.println(new String(responseBody));
                ArrayList<SortsStatistics> sortsStatistics;
                JsonToArrayList<SortsStatistics> converter = new JsonToArrayList<>();
                sortsStatistics = converter.convert(responseBody,SortsStatistics.class);
                System.out.println("sorts   "+sortsStatistics.toString());
                buildPieChart(sortsStatistics);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
        getStatistic.commit();

        GetRequest getStatisticPerCountry = new GetRequest(UrlData.getSortingsAllocationPerCountry(), getActivity(), new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                ArrayList<FurStatisticPerCountry> statisticPerCountries;
                JsonToArrayList<FurStatisticPerCountry> converter = new JsonToArrayList<>();
                statisticPerCountries = converter.convert(responseBody,FurStatisticPerCountry.class);
                System.out.println("sorts   "+statisticPerCountries.toString());
                buildBarCharts(statisticPerCountries);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
        getStatisticPerCountry.commit();


        return view;
    }


    private void buildPieChart(ArrayList<SortsStatistics> statistics){
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5,10,5,5);

        pieChart.setDragDecelerationFrictionCoef(0.97f);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(61f);



        ArrayList<PieEntry> values = new ArrayList<>();
        for (SortsStatistics st:statistics){
            values.add(new PieEntry(st.getAllocation(),FurSortingTypes.checkSortingType(getActivity(),st.getSorting())));
        }

        PieDataSet pieDataSet = new PieDataSet(values,"");

        pieDataSet.setSliceSpace(3f);
        pieDataSet.setSelectionShift(5f);
        pieDataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        PieData pieData = new PieData(pieDataSet);
        pieData.setValueTextSize(9f);
        pieData.setValueTextColor(Color.DKGRAY);

        pieChart.setData(pieData);
        pieChart.setNoDataText("");
        pieChart.invalidate();
    }

    private  void buildBarCharts(ArrayList<FurStatisticPerCountry> statistic){
        LinearLayout linearLayout = view.findViewById(R.id.chart_container);


        for (FurStatisticPerCountry st:statistic) {
            BarChart barChart = new BarChart(getContext());
            barChart.getDescription().setText("");
            barChart.getDescription().setEnabled(true);
            barChart.setFitBars(true);

            ArrayList<BarEntry> values = new ArrayList<>();
            JsonArray jsonArray = st.getSortingStatistics();
            ArrayList<SortsStatistics> sorts = new ArrayList<>();
            for (int i=0; i<jsonArray.size();i++) {
                JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
                JsonToArrayList<SortsStatistics> sortObj = new JsonToArrayList<>();
                sorts.add(sortObj.convert(jsonObject,SortsStatistics.class));
            }
            Collections.sort(sorts,new sortStatisticById());
            System.out.println("sorted array" + sorts.toString());

            for (int i=0; i<sorts.size();i++) {
                String label = FurSortingTypes.checkSortingType(getActivity(),sorts.get(i).getSorting())+" ("+sorts.get(i).getAllocation()+"%)";
                BarEntry barEntry = new BarEntry(i+1,sorts.get(i).getAllocation(),label);
                values.add(barEntry);
            }


            BarDataSet barDataSet = new BarDataSet(values,st.getName());
            barDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
            barDataSet.setDrawValues(true);
            barDataSet.setValueFormatter(new IValueFormatter() {
                @Override
                public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                    return entry.getData().toString();
                }
            });

            BarData barData = new BarData(barDataSet);
            barChart.setData(barData);


            linearLayout.addView(barChart);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) barChart.getLayoutParams();
            int pixelValue = getPixelValue(getContext(),200);
            params.topMargin = getPixelValue(getContext(),20);
            params.height = pixelValue;
            barChart.setLayoutParams(params);
        }
    }

    private class sortStatisticById implements Comparator<SortsStatistics>{

        @Override
        public int compare(SortsStatistics o1, SortsStatistics o2) {
            return o1.getSorting()-o2.getSorting();
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
}
