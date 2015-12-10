package com.darkphoton.data_visualiser_project.partials;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.util.Log;
import android.util.Pair;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;

import com.darkphoton.data_visualiser_project.MainActivity;
import com.darkphoton.data_visualiser_project.R;
import com.darkphoton.data_visualiser_project.data.processed.PCountry;
import com.darkphoton.data_visualiser_project.data.processed.PIndicator;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;
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
    private RelativeLayout relativeLayout;

    public PieChartPanel(Context context) {
        super(context);
        setLayoutParams(new LayoutParams(size.x, size.y));
        setBackgroundColor(Color.WHITE);
        setGravity(Gravity.CENTER);
        setY(-size.y);
        relativeLayout = new RelativeLayout(context);
        relativeLayout.setLayoutParams(
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));


        pieChart = new PieChart(context);
        pieChart.setLayoutParams(
                new ViewGroup.LayoutParams((int) (size.y * 1f), (int) (size.y * 1f)));
        pieChart.setOnChartGestureListener(null);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleRadius(15f);
        pieChart.setHoleColorTransparent(true);
        pieChart.setTransparentCircleRadius(15f);
        pieChart.setDrawSliceText(false);
        relativeLayout.addView(pieChart);


        Legend l = pieChart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        l.setXEntrySpace(0);
        l.setYEntrySpace(0);

    }

    public void open(ArrayList<Pair<String, Float>> data, PIndicator indicator) {
        addData(data);
        pieChart.invalidate();
        pieChart.setDescription(indicator.getTitle());
        pieChart.setDescriptionTextSize(30);
        addView(relativeLayout);
        setY(-size.y);
        TranslateAnimation animation = new TranslateAnimation(0, 0, 0, size.y);
        animation.setInterpolator(new DecelerateInterpolator(1.5f));
        animation.setDuration(500);
        animation.setFillAfter(true);
        this.startAnimation(animation);

    }

    public void close() {
        setY(0);
        TranslateAnimation animation = new TranslateAnimation(0, 0, 0, -size.y);
        animation.setInterpolator(new DecelerateInterpolator(1.5f));
        animation.setDuration(500);
        animation.setFillAfter(true);
        startAnimation(animation);
        removeAllViews();
    }

    public void addData(ArrayList<Pair<String, Float>> dataP) {
        //arraylist for entry for PieChart


        averageIndicater = new ArrayList<>();
        for (int i = 0; i < dataP.size(); i++) {
            if(!dataP.get(i).second.toString().equals("NaN")){
                averageIndicater.add(new Entry(dataP.get(i).second, i));
                Log.d("values",""+dataP.get(i).second);
            }
        }

        country = new ArrayList<>();
        for (int i = 0; i < dataP.size(); i++) {
            if(!dataP.get(i).second.toString().equals("NaN")) {
                country.add(dataP.get(i).first);
            }
        }

        // arraylist to contain colors for each section of piechart
        ArrayList<Integer> colors = new ArrayList<>();

        colors.add(Color.rgb(51,153,102));
        colors.add(Color.rgb(153,255,102));
        colors.add(Color.rgb(204,153,0));
        colors.add(Color.rgb(153,102,0));
        colors.add(Color.rgb(204,51,0));
        colors.add(Color.rgb(0,51,102));
        colors.add(Color.rgb(102,153,153));
        colors.add(Color.rgb(102,204,255));
        colors.add(Color.rgb(204,153,155));
        colors.add(Color.rgb(255,51,204));
        colors.add(Color.rgb(153,51,255));
        colors.add(Color.rgb(153,255,204));
        colors.add(Color.rgb(242,242,242));
        colors.add(Color.rgb(102,0,51));
        colors.add(Color.rgb(0,0,255));
        colors.add(Color.rgb(0,153,0));
        colors.add(Color.rgb(255,204,102));
        colors.add(Color.rgb(255,230,204));
        colors.add(Color.rgb(64,64,64));
        colors.add(Color.rgb(77,255,77));






            // creating PieDataset to hold the values

            PieDataSet dataSet = new PieDataSet(averageIndicater, "Countries");
            dataSet.setSelectionShift(5f);
            dataSet.setSliceSpace(0f);
            dataSet.setColors(colors);


            // creating pieData to disaply the final piechart
            PieData data = new PieData(country, dataSet);
            data.setValueTextSize(15f);
            pieChart.setData(data);

        }

    }

