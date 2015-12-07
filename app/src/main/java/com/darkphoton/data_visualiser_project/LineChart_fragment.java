package com.darkphoton.data_visualiser_project;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;


public class LineChart_fragment extends Fragment {

    private LineChart lineChart;
    private RelativeLayout mainlayout;
    private RelativeLayout chartLayout;

    private TextView xAxisLabel;
    private TextView yAxisLabel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_line_chart_fragment , container , false);

        if (view != null) {

                mainlayout = (RelativeLayout) view.findViewById(R.id.mainLayout);

                chartLayout = (RelativeLayout) view.findViewById(R.id.chartLayout);

//              customising the X Axis Title
                xAxisLabel = (TextView) view.findViewById(R.id.xAxis);
                xAxisLabel.setText("Year");
                xAxisLabel.setTextSize(20f);
                xAxisLabel.setTextColor(Color.BLACK);

//              customising the Y Axis Title
                yAxisLabel = (TextView) view.findViewById(R.id.yAxis);
                yAxisLabel.setText("Value");
                yAxisLabel.setTextSize(20f);
                yAxisLabel.setTextColor(Color.BLACK);

                //create line chart
                lineChart = (LineChart) view.findViewById(R.id.lineChart);

//                RelativeLayout.LayoutParams relativeParams = new RelativeLayout.LayoutParams(
//                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//                chartLayout.addView(lineChart, relativeParams);

//                chartLayout.addView(lineChart);

//                mainlayout.addView(chartLayout);

                //setting up the animation for the graph
                lineChart.animateXY(7000, 500);

                //line chart customisation
                lineChart.setDescription("");
                lineChart.setNoDataTextDescription("No Data Provided");

                // enable touch gesture
                lineChart.setTouchEnabled(true);

                //enable scaling
                lineChart.setDragEnabled(true);
                lineChart.setScaleEnabled(true);
                lineChart.setDrawGridBackground(false);

                //pinch zoom enabled to prevent x and y axis scaling separately
                lineChart.setPinchZoom(true);

                //setting the background colour of the chart
                lineChart.setBackgroundColor(Color.WHITE);

                //setting up the data for the chart
                LineData data = new LineData();
                data.setValueTextColor(Color.LTGRAY);

                //adding the data to the line chart
                lineChart.setData(data);

                //sets the data for current chart
                setDataValues();

                //getting and customising the legend
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

        return view;
    }



    //set the data for the line graph
    private void setDataValues() {

        //values for the X Axis
         ArrayList<String> xVals = new ArrayList<String>();
        //random some method for getting some values
        for (int i = 0; i < 10; i++) {

            if(i<10){ xVals.add("200" + (i) + "");}
            else{
                xVals.add("20"+(i));
            }
        }

        //values for the Y axis
        ArrayList<Entry> yVals = new ArrayList<Entry>();
        //random method for getting some values
        for (int i = 0; i < 10; i++) {

            yVals.add(new Entry((i*i), i));

        }

        // create the dataset and give it a type
        LineDataSet set1 = new LineDataSet(yVals, "Value");
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

        // create a data object with the dataset
        LineData data = new LineData(xVals, set1);
        data.setValueTextColor(Color.BLACK);
        data.setValueTextSize(9f);

        // set data
        lineChart.setData(data);
    }


}
