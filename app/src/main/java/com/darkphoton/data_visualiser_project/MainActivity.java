package com.darkphoton.data_visualiser_project;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {
    private PieChart pieChart;
    private float [] avarageTopCountires;
    private String [] topCountries;
    private RelativeLayout pieChartLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pieChartLayout = (RelativeLayout) findViewById(R.id.R_id_PieChartLayout);
        //pieChart.setBackgroundColor();

        // initialising both arrays
        avarageTopCountires = new float[] {50,70,72,75,78};
        topCountries = new String[] {"Japan" , "Russia" , "France" , "UK" , "USA"};

        //creating PieChart and adding to layout
        pieChart = new PieChart(this);
        pieChartLayout.addView(pieChart);

        pieChart.setUsePercentValues(true);
        pieChart.setDescription("Top Counties that are suitable for you  ");

        //setting the hole
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColorTransparent(true);
        pieChart.setHoleRadius(20f);
        pieChart.setTransparentCircleRadius(30f);

        pieChart.setMinimumWidth(1000);
        pieChart.setMinimumHeight(1000);

        // method to add the data to the pie chart
        addDataToPieChart();
    }
    public void addDataToPieChart(){
        // create Entry arraylist to store the average values
        ArrayList <Entry> averageCountryValue = new ArrayList<Entry>();

        for(int i = 0 ; i < avarageTopCountires.length ; i++){
            averageCountryValue.add(new Entry(avarageTopCountires[i], i));
        };

        // arraylist to store the top countries
        ArrayList<String> topCountriesValue = new ArrayList<String>();

        for(int i = 0; i < topCountries.length ; i++){
            topCountriesValue.add(topCountries[i]);
        };

        // piedataset stores the entries  and displays them as legend
        PieDataSet dataSet = new PieDataSet(averageCountryValue, "Average");
        dataSet.setSliceSpace(0f);
        dataSet.setSelectionShift(0f);

        //arraylist to store the colours of each section in pie chart
        ArrayList<Integer>colorPieChart = new ArrayList<Integer>();
        for (int i = 0 ; i< 5 ; i++){
            colorPieChart.add(ColorTemplate.VORDIPLOM_COLORS [i]);
        };

        dataSet.setColors(colorPieChart);

        //represent the data
        PieData data = new PieData(topCountriesValue, dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(15f);

        pieChart.setData(data);
        pieChart.highlightValue(null);

        pieChart.invalidate();

    };
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
