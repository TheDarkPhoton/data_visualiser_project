package com.darkphoton.data_visualiser_project.partials;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.util.Pair;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.darkphoton.data_visualiser_project.MainActivity;
import com.darkphoton.data_visualiser_project.R;
import com.darkphoton.data_visualiser_project.data.Processor;
import com.darkphoton.data_visualiser_project.data.processed.PCountry;
import com.darkphoton.data_visualiser_project.data.processed.PIndicator;
import com.darkphoton.data_visualiser_project.data.raw.RCountry;
import com.darkphoton.data_visualiser_project.data.raw.RData;
import com.darkphoton.data_visualiser_project.data.raw.RIndicator;

import java.util.ArrayList;

/**
 * Created by darkphoton on 10/12/15.
 */
public class IndicatorItem extends RelativeLayout {
    private Point size = MainActivity.screen_size;
    private Processor _data;
    private PCountry _country;
    private PIndicator _indicator;

    public IndicatorItem(Context context, Processor data, PCountry country, PIndicator indicator) {
        super(context);

        _data = data;
        _country = country;
        _indicator = indicator;

        setLayoutParams(new LayoutParams(size.x / 2, size.y));
        setBackgroundColor(CountryItem.transparent_colours.get(4));

        TextView title = new TextView(context);
        title.setLayoutParams(new ViewGroup.LayoutParams(size.x / 2, size.y / 3));
        title.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
        title.setText(indicator.getName());
        title.setTextSize(size.y / 40);
        title.setTypeface(null, Typeface.BOLD);
        addView(title);
        title.setBackgroundColor(CountryItem.colours.get(4));

        TextView average = new TextView(context);
        average.setLayoutParams(new ViewGroup.LayoutParams(size.x / 2, size.y / 3));
        average.setGravity(Gravity.CENTER);
        average.setText("5 Year Average: " + indicator.getFormatedAverage());
        average.setTextSize(size.y / 40);
        average.setY(size.y / 3);
        addView(average);

        initButtons(context);
    }

    private void initButtons(Context context){
        Typeface font = Typeface.createFromAsset( context.getAssets(), "fontawesome-webfont.ttf" );

        RelativeLayout infoLayout = new RelativeLayout(context);
        infoLayout.setLayoutParams(new LayoutParams((size.x / 2) / 3, size.y / 3));
        infoLayout.setY(size.y / 3 * 2);
        infoLayout.setGravity(Gravity.CENTER);
        addView(infoLayout);

        RelativeLayout pieLayout = new RelativeLayout(context);
        pieLayout.setLayoutParams(new LayoutParams((size.x / 2) / 3, size.y / 3));
        pieLayout.setX((size.x / 2) / 3);
        pieLayout.setY(size.y / 3 * 2);
        pieLayout.setGravity(Gravity.CENTER);
        addView(pieLayout);

        RelativeLayout lineLayout = new RelativeLayout(context);
        lineLayout.setLayoutParams(new LayoutParams((size.x / 2) / 3, size.y / 3));
        lineLayout.setX((size.x / 2) / 3 * 2);
        lineLayout.setY(size.y / 3 * 2);
        lineLayout.setGravity(Gravity.CENTER);
        addView(lineLayout);

        int diameter = infoLayout.getLayoutParams().width;
        if (infoLayout.getLayoutParams().height < diameter)
            diameter = infoLayout.getLayoutParams().height;
        diameter *= 0.5;

        TextView info = new TextView(context);
        info.setLayoutParams(new ViewGroup.LayoutParams(diameter, diameter));
        info.setTypeface(font);
        info.setTextSize(diameter * 0.2f);
        info.setGravity(Gravity.CENTER);
        info.setBackground(ContextCompat.getDrawable(context, R.drawable.circle));
        info.setText(context.getResources().getString(R.string.information));
        info.setOnClickListener(_infoListener);
        infoLayout.addView(info);

        TextView pie = new TextView(context);
        pie.setLayoutParams(new ViewGroup.LayoutParams(diameter, diameter));
        pie.setTypeface(font);
        pie.setTextSize(diameter * 0.2f);
        pie.setGravity(Gravity.CENTER);
        pie.setBackground(ContextCompat.getDrawable(context, R.drawable.circle));
        pie.setText(context.getResources().getString(R.string.pie_chart));
        pie.setOnClickListener(_pieListener);
        pieLayout.addView(pie);

        TextView line = new TextView(context);
        line.setLayoutParams(new ViewGroup.LayoutParams(diameter, diameter));
        line.setTypeface(font);
        line.setTextSize(diameter * 0.2f);
        line.setGravity(Gravity.CENTER);
        line.setBackground(ContextCompat.getDrawable(context, R.drawable.circle));
        line.setText(context.getResources().getString(R.string.line_chart));
        line.setOnClickListener(_lineListener);
        lineLayout.addView(line);
    }

    private ArrayList<Pair<String, Double>> getPieChartData(){
        ArrayList<Pair<String, Double>> data = new ArrayList<>();

        for (PCountry country : _data.getCountries()) {
            PIndicator indicator = country.getIndicator(_indicator.getId());
            Pair<String, Double> d = new Pair<>(country.getName(), indicator.getAverage());
            data.add(d);
        }

        return data;
    }

    private Pair<String, RIndicator> getLineChartData(){
        RCountry country = MainActivity.rowData.getCountries().get(_country.getId());
        RIndicator indicator = country.getIndicators().get(_indicator.getId());

        return new Pair<>(_country.getName(), indicator);
    }

    private OnClickListener _infoListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.i("INFO LISTENER", "Pressed");
            if (MainActivity.activePanel == null) {
                MainActivity.countries.setEnabled(false);
                MainActivity.infoPanel.open(_indicator);
                MainActivity.activePanel = MainActivity.infoPanel;
            }
        }
    };

    private OnClickListener _pieListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.i("PIE LISTENER", "Pressed");
            if (MainActivity.activePanel == null) {
                MainActivity.countries.setEnabled(false);
                MainActivity.pieChartPanel.open(getPieChartData());
                MainActivity.activePanel = MainActivity.pieChartPanel;
            }
        }
    };

    private OnClickListener _lineListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.i("LINE LISTENER", "Pressed");
            if (MainActivity.activePanel == null) {
                MainActivity.countries.setEnabled(false);
                MainActivity.lineGraphPanel.open(getLineChartData());
                MainActivity.activePanel = MainActivity.lineGraphPanel;
            }
        }
    };
}
