package com.darkphoton.data_visualiser_project.partials;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Handler;
import android.util.Pair;
import android.view.Gravity;
import android.view.MotionEvent;
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
import java.util.Random;

/**
 * Created by darkphoton on 10/12/15.
 */
public class PieChartPanel extends PartialPanel {
    private Point size = MainActivity.screen_size;
    private RelativeLayout relativeLayout;
    private PieChart pieChart;

    private ArrayList<Integer> colours = new ArrayList<>();

    public PieChartPanel(Context context) {
        super(context);
        setLayoutParams(new LayoutParams(size.x, size.y));
        setBackgroundColor(Color.WHITE);
        setGravity(Gravity.CENTER);
        setY(-size.y);

        pieChart = new PieChart(context);
        pieChart.setLayoutParams(
                new ViewGroup.LayoutParams((int) (size.x * 0.9f), (int) (size.y * 0.9f)));
        pieChart.setOnChartGestureListener(null);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleRadius(35f);
        pieChart.setHoleColorTransparent(true);
        pieChart.setTransparentCircleRadius(37f);
        initColours(20);

        addView(pieChart);
    }

    private void initColours(int x){
        Random rand = new Random();

        for (int i = 0; i < x; i++) {
            int r = rand.nextInt(256);
            int g = rand.nextInt(256);
            int b = rand.nextInt(256);
            colours.add(Color.argb(255, r, g, b));
        }
    }
    
    public void open(List<Pair<String, Double>> data, String indicatorTitle){
        _closed = false;

        addData(data, indicatorTitle);
        pieChart.invalidate();

        setY(-size.y);
        TranslateAnimation animation = new TranslateAnimation(0, 0, 0, size.y);
        animation.setInterpolator(new DecelerateInterpolator(1.5f));
        animation.setDuration(500);
        animation.setFillAfter(true);
        this.startAnimation(animation);
    }

    public void close(){
        _closed = true;

        setY(0);
        TranslateAnimation animation = new TranslateAnimation(0, 0, 0, -size.y);
        animation.setInterpolator(new DecelerateInterpolator(1.5f));
        animation.setDuration(500);
        animation.setFillAfter(true);
        startAnimation(animation);
    }

    public void addData(List<Pair<String, Double>> pairData, String indicatorTitle){
        ArrayList<Entry> averageIndicater = new ArrayList<>();
        ArrayList<String> country = new ArrayList<>();

        for (int i = 0; i < pairData.size(); i++) {
            String countryName = pairData.get(i).first;
            float value = pairData.get(i).second.floatValue();

            if (Double.isNaN(value))
                continue;

            country.add(countryName);
            averageIndicater.add(new Entry(value, i));
        }

        pieChart.setDescription(indicatorTitle);

        // creating PieDataset to hold the values
        PieDataSet dataSet = new PieDataSet(averageIndicater, "");
        dataSet.setSelectionShift(5f);
        dataSet.setSliceSpace(0f);
        dataSet.setColors(colours);

        // creating pieData to disaply the final piechart
        PieData data = new PieData(country, dataSet);
        data.setValueTextSize(15f);
        pieChart.setData(data);
    }
}
