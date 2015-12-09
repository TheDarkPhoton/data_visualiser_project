package com.darkphoton.data_visualiser_project;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.darkphoton.data_visualiser_project.data.processed.PCountry;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class PieChart_Fragment extends Fragment {
    private PieChart pieChart;
    private ArrayList<Entry> averageValueTopCountriesValue;
    private ArrayList<String> averageTopCountriesName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pie_chart , container , false);

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
//            piechartIsPressed();

        }

        return view;
    }

    // method to add the data to the piechart and dispaly it
    public void addData(final List<PCountry> countries){
        //arraylist for entry for PieChart
        averageValueTopCountriesValue = new ArrayList<>();
        for (int i = 0; i < countries.size(); i++) {
            averageValueTopCountriesValue.add(new Entry((float)countries.get(i).getValue(), i));
        }

        averageTopCountriesName = new ArrayList<>();
        for (int i = 0 ; i < countries.size() ; i++){
           averageTopCountriesName.add(countries.get(i).getName());
        }

        // arraylist to contain colors for each section of piechart

        final ArrayList <Integer> colors = new ArrayList<>();

        for (int i = 0 ; i < countries.size() ; i++){
            colors.add(ColorTemplate.VORDIPLOM_COLORS[i]);
        }

        // creating PieDataset to hold the values
        PieDataSet dataSet = new PieDataSet(averageValueTopCountriesValue , "Countries");
        dataSet.setSelectionShift(5f);
        dataSet.setSliceSpace(0f);
        dataSet.setColors(colors);

        // creating pieData to disaply the final piechart
        PieData data = new PieData(averageTopCountriesName , dataSet);
        data.setValueTextSize(15f);
        pieChart.setData(data);
        pieChart.animateX(1500, Easing.EasingOption.EaseInCirc);

        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry entry, int i, Highlight highlight) {
                int countrypressed = entry.getXIndex();

                Log.d("country selected" , ""+ countries.get(countrypressed).getName());
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                List_View_fragment secondframent = new List_View_fragment(countries.get(countrypressed));

                fragmentTransaction.replace(R.id.fragmentchart , secondframent);
                fragmentTransaction.addToBackStack("piechart");
                fragmentTransaction.commit();

            }

            @Override
            public void onNothingSelected() {

            }
        });


    }

}
