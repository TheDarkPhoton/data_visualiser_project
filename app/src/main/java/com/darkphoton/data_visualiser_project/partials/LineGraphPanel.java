package com.darkphoton.data_visualiser_project.partials;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.util.Pair;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.darkphoton.data_visualiser_project.MainActivity;
import com.darkphoton.data_visualiser_project.data.raw.RData;
import com.darkphoton.data_visualiser_project.data.raw.RIndicator;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by darkphoton on 10/12/15.
 */
public class LineGraphPanel extends PartialPanel {
    private Point size = MainActivity.screen_size;

    private LineChart lineChart;
    private TextView xAxisLabel;
    private TextView yAxisLabel;

    private ArrayList<Entry> yVals;
    private ArrayList<String> xVals;

    private String indicName;

    public LineGraphPanel(Context context) {
        super(context);
        setLayoutParams(new RelativeLayout.LayoutParams(size.x, size.y));
        setBackgroundColor(Color.WHITE);
        setY(size.y);
        setGravity(Gravity.CENTER);

//        RelativeLayout relativeLayout= new RelativeLayout(context);
//        relativeLayout.setLayoutParams(
//                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//        addView(relativeLayout);

//        xAxisLabel = new TextView(context);
//        xAxisLabel.setText("Year");
//        xAxisLabel.setTextSize(20f);
//        xAxisLabel.setTextColor(Color.BLACK);
//        RelativeLayout.LayoutParams relativeParams1 = new LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
//        relativeParams1.addRule(Gravity.CENTER);
//        relativeParams1.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

//        yAxisLabel = new TextView(context);
//        yAxisLabel.setText(indicName);
//        yAxisLabel.setTextSize(20f);
//        yAxisLabel.setTextColor(Color.BLACK);
//        RelativeLayout.LayoutParams relativeParams2 = new LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
//        relativeParams2.addRule(RelativeLayout.ALIGN_LEFT, RelativeLayout.TRUE);

        lineChart = new LineChart(context);
        lineChart.setLayoutParams(
                new ViewGroup.LayoutParams((int) (size.x * 0.9), (int) (size.y * 0.9)));

        addView(lineChart);

//        relativeLayout.addView(xAxisLabel, relativeParams1);
//        relativeLayout.addView(yAxisLabel, relativeParams2);

        lineChart.animateXY(7000, 500);
        lineChart.setDescription("");
        lineChart.setNoDataTextDescription("This indicator does not have any valid data");
        lineChart.setTouchEnabled(false);
        lineChart.setDragEnabled(false);
        lineChart.setScaleEnabled(true);
        lineChart.setDrawGridBackground(false);
        lineChart.setPinchZoom(true);
        lineChart.setBackgroundColor(Color.WHITE);

        LineData data = new LineData();
        data.setValueTextColor(Color.LTGRAY);
        lineChart.setData(data);

        Legend legend = lineChart.getLegend();
        legend.setForm(Legend.LegendForm.LINE);
        legend.setTextColor(Color.LTGRAY);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setTextColor(Color.BLACK);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawAxisLine(false);
        xAxis.setAvoidFirstLastClipping(true);

        YAxis yAxisl = lineChart.getAxisLeft();
        yAxisl.setTextColor(Color.BLACK);
        yAxisl.setDrawAxisLine(true);
        yAxisl.setStartAtZero(false);
        YAxis yAxisr = lineChart.getAxisRight();
        yAxisr.setEnabled(false);
    }

    public void open(Pair<String, RIndicator> data){
        _closed = false;

        setDataValues(data);
        lineChart.invalidate();

        setY(size.y);
        TranslateAnimation animation = new TranslateAnimation(0, 0, 0, -size.y);
        animation.setInterpolator(new DecelerateInterpolator(1.5f));
        animation.setDuration(500);
        animation.setFillAfter(true);
        this.startAnimation(animation);
    }

    public void close(){
        _closed = true;

        setY(0);
        TranslateAnimation animation = new TranslateAnimation(0, 0, 0, size.y);
        animation.setInterpolator(new DecelerateInterpolator(1.5f));
        animation.setDuration(500);
        animation.setFillAfter(true);
        startAnimation(animation);
    }

    public void setDataValues(Pair<String, RIndicator> rData){

        RIndicator indicator = rData.second;
        indicName =  "";

        ArrayList<RData> tempData = new ArrayList<>(indicator.getData().values());
        Collections.sort(tempData, new Comparator<RData>() {
            @Override
            public int compare(RData lhs, RData rhs) {
                return lhs.getDate().compareTo(rhs.getDate());
            }
        });

        xVals = new ArrayList<>();
        yVals = new ArrayList<>();
        for (int i = 0; i < tempData.size(); i++) {
            RData d = tempData.get(i);
            if (!d.isValid())
                continue;

            xVals.add(d.getDate());
            yVals.add(new Entry((float) d.getValue(), i));
        }

        LineDataSet set1 = new LineDataSet(yVals, indicName);
        set1.setDrawCubic(true);
        set1.setCubicIntensity(0.2f);
        set1.setAxisDependency(YAxis.AxisDependency.LEFT);
        set1.setColor(ColorTemplate.getHoloBlue());
        set1.setCircleColor(ColorTemplate.getHoloBlue());
        set1.setLineWidth(2f);
        set1.setCircleSize(3f);
        set1.setFillAlpha(65);
        set1.setFillColor(ColorTemplate.getHoloBlue());
        set1.setHighLightColor(Color.rgb(244, 117, 117));
        set1.setDrawCircleHole(true);
        set1.setCircleColorHole(Color.WHITE);

        LineData data = new LineData(xVals, set1);
        data.setValueTextColor(Color.BLACK);
        data.setValueTextSize(9f);
        lineChart.setData(data);
    }
}
