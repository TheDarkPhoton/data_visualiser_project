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
    private boolean _closed = true;

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
                new ViewGroup.LayoutParams((int) (size.y * 0.8f), (int) (size.y * 0.8f)));
        pieChart.setOnChartGestureListener(null);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleRadius(35f);
        pieChart.setHoleColorTransparent(true);
        pieChart.setTransparentCircleRadius(37f);
        pieChart.setDescription("");
        initColours(20);

        relativeLayout.addView(pieChart);
        addView(relativeLayout);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return _closed || super.onInterceptTouchEvent(ev);
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
    
    public void open(ArrayList<Pair<String, Double>> data){
        _closed = false;

        addData(data);
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

//        removeView(relativeLayout);
//        Handler h = new Handler();
//        h.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                PieChartPanel.this.removeView(relativeLayout);
//            }
//        }, 500);
    }

    public void addData(ArrayList<Pair<String, Double>> pairData){
        ArrayList<Entry> averageIndicater = new ArrayList<>();
        ArrayList<String> country = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            averageIndicater.add(new Entry(pairData.get(i).second.floatValue(), i));
            country.add(pairData.get(i).first);
        }

        // creating PieDataset to hold the values
        PieDataSet dataSet = new PieDataSet(averageIndicater, "Countries");
        dataSet.setSelectionShift(5f);
        dataSet.setSliceSpace(0f);
        dataSet.setColors(colours);

        // creating pieData to disaply the final piechart
        PieData data = new PieData(country, dataSet);
        data.setValueTextSize(15f);
        pieChart.setData(data);
    }
}
