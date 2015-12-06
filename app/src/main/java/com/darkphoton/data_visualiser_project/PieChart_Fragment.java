package com.darkphoton.data_visualiser_project;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class PieChart_Fragment extends Fragment {
    private PieChart pieChart;
    private float [] valuesForCountries;
    private String [] topCountries;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pie_chart , container , false);

        //initialing arrays
        valuesForCountries = new float[]{ 40, 65, 72 , 76 , 82};
        topCountries = new String[] {"China" , "France" , "Japan" , "UK" , "USA" };

        // check if view is created to prevent NullPointerException
        if (view != null) {
            // creating piechart
            pieChart = (PieChart) view.findViewById(R.id.piechartGraph);
            pieChart.setUsePercentValues(true);
            pieChart.setDescription("Top 5 countries based on input ");

            //look of piechart
            pieChart.setDrawHoleEnabled(true);
            pieChart.setHoleRadius(35f);
            pieChart.setHoleColorTransparent(true);
            pieChart.setTransparentCircleRadius(37f);

            addData();
        }

        return view;
    }

    public void addData(){
        //arraylist for entry for PieChart

        ArrayList<Entry> avarageValueTopCountiresValue = new ArrayList<>();

        for (int i = 0 ; i < valuesForCountries.length ; i++){
            avarageValueTopCountiresValue.add(new Entry(valuesForCountries[i] , i));
        }

        // arraylist to hold the countries
        ArrayList<String> avarageTopCountriesName = new ArrayList<>();

        for (int i = 0 ; i < topCountries.length ; i++){
            avarageTopCountriesName.add(topCountries[i]);
        }

        // arraylist to contain colors for each section of piechart

        ArrayList <Integer> colors = new ArrayList<>();

        for (int i = 0 ; i < valuesForCountries.length ; i++){
            colors.add(ColorTemplate.VORDIPLOM_COLORS[i]);
        }

        // creating PieDataset to hold the values
        PieDataSet dataSet = new PieDataSet(avarageValueTopCountiresValue , "Countries");
        dataSet.setSelectionShift(5f);
        dataSet.setSliceSpace(0f);
        dataSet.setColors(colors);

        // creating pieData to disaply the final piechart
        PieData data = new PieData(avarageTopCountriesName , dataSet);
        data.setValueTextSize(15f);
        pieChart.setData(data);
        pieChart.animateX( 1500 , Easing.EasingOption.EaseInCirc);
       // pieChart.spin( 1000,0,-360f, Easing.EasingOption.EaseInElastic);
    }

}
