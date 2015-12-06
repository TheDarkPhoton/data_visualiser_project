package com.darkphoton.data_visualiser_project;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;

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
        valuesForCountries = new float[]{ 60, 65, 72 , 76 , 82};
        topCountries = new String[] {"China" , "France" , "Japan" , "UK" , "USA" };

        // check if view is created to prevent NullPointerException
        if (view != null) {

        }
        else {

        }

        return view;
    }

}
