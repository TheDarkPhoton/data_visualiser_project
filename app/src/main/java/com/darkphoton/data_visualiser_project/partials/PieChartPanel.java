package com.darkphoton.data_visualiser_project.partials;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;

import com.darkphoton.data_visualiser_project.MainActivity;
import com.darkphoton.data_visualiser_project.data.processed.PCountry;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by darkphoton on 10/12/15.
 */
public class PieChartPanel extends PartialPanel {
    private Point size = MainActivity.screen_size;
    private PieChart pieChart;
    private ArrayList<Entry> averageIndicater;
    private ArrayList<String> country;


    public PieChartPanel(Context context) {
        super(context);
        setLayoutParams(new LayoutParams(size.x, size.y));
        setBackgroundColor(Color.GREEN);
        setGravity(Gravity.CENTER);
        setY(-size.y);


        RelativeLayout relativeLayout= new RelativeLayout(context);
        relativeLayout.setLayoutParams(
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        addView(relativeLayout);

        pieChart = new PieChart(context);
        pieChart.setLayoutParams(
                new ViewGroup.LayoutParams((int) (size.y * 0.8f), (int) (size.y * 0.8f)));
        pieChart.setOnChartGestureListener(null);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleRadius(35f);
        pieChart.setHoleColorTransparent(true);
        pieChart.setTransparentCircleRadius(37f);
        pieChart.setDescription("testing pie chart");

        relativeLayout.addView(pieChart);
    }
    
    public void open(){
        addData();
        pieChart.invalidate();
        setY(-size.y);
        TranslateAnimation animation = new TranslateAnimation(0, 0, 0, size.y);
        animation.setInterpolator(new DecelerateInterpolator(1.5f));
        animation.setDuration(500);
        animation.setFillAfter(true);
        this.startAnimation(animation);
    }

    public void close(){
        setY(0);
        TranslateAnimation animation = new TranslateAnimation(0, 0, 0, -size.y);
        animation.setInterpolator(new DecelerateInterpolator(1.5f));
        animation.setDuration(500);
        animation.setFillAfter(true);
        startAnimation(animation);
    }

    public void addData(){
        //arraylist for entry for PieChart




        averageIndicater = new ArrayList<>();
        averageIndicater.add(new Entry(10f,0));
        averageIndicater.add(new Entry(20f,1));
        averageIndicater.add(new Entry(30f,2));

        country = new ArrayList<>();
        country.add("1");
        country.add("2");
        country.add("3");

        // arraylist to contain colors for each section of piechart

        ArrayList <Integer> colors = new ArrayList<>();

        for (int i = 0 ; i < country.size() ; i++){
            colors.add(ColorTemplate.VORDIPLOM_COLORS[i]);
        }

        // creating PieDataset to hold the values
        PieDataSet dataSet = new PieDataSet(averageIndicater, "Countries");
        dataSet.setSelectionShift(5f);
        dataSet.setSliceSpace(0f);
        dataSet.setColors(colors);

        // creating pieData to disaply the final piechart
        PieData data = new PieData(country , dataSet);
        data.setValueTextSize(15f);
        pieChart.setData(data);
    }









}
