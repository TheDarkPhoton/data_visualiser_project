package com.darkphoton.data_visualiser_project.partials;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.darkphoton.data_visualiser_project.MainActivity;
import com.darkphoton.data_visualiser_project.R;
import com.darkphoton.data_visualiser_project.data.processed.PIndicator;

/**
 * Created by darkphoton on 10/12/15.
 */
public class IndicatorItem extends RelativeLayout {
    private Point size = MainActivity.screen_size;
    private PIndicator _indicator;

    public IndicatorItem(Context context, PIndicator indicator) {
        super(context);

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
                MainActivity.pieChartPanel.open();
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
                MainActivity.lineGraphPanel.open();
                MainActivity.activePanel = MainActivity.lineGraphPanel;
            }
        }
    };
}
