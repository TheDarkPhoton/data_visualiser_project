package com.darkphoton.data_visualiser_project;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.darkphoton.data_visualiser_project.data.processed.PIndicator;

/**
 * Created by darkphoton on 10/12/15.
 */
public class IndicatorItem extends RelativeLayout {
    private Point size = MainActivity.screen_size;

    public IndicatorItem(Context context, PIndicator indicator) {
        super(context);


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
        average.setText("5 Year Average: " + indicator.getAverage());
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
//        info.setTextColor(CountryItem.colours.get(3));
        info.setBackground(ContextCompat.getDrawable(context, R.drawable.circle));
        info.setText(context.getResources().getString(R.string.information));
        infoLayout.addView(info);

        TextView pie = new TextView(context);
        pie.setLayoutParams(new ViewGroup.LayoutParams(diameter, diameter));
        pie.setTypeface(font);
        pie.setTextSize(diameter * 0.2f);
        pie.setGravity(Gravity.CENTER);
//        pie.setTextColor(CountryItem.colours.get(3));
        pie.setBackground(ContextCompat.getDrawable(context, R.drawable.circle));
        pie.setText(context.getResources().getString(R.string.pie_chart));
        pieLayout.addView(pie);

        TextView line = new TextView(context);
        line.setLayoutParams(new ViewGroup.LayoutParams(diameter, diameter));
        line.setTypeface(font);
        line.setTextSize(diameter * 0.2f);
        line.setGravity(Gravity.CENTER);
//        line.setTextColor(CountryItem.colours.get(3));
        line.setBackground(ContextCompat.getDrawable(context, R.drawable.circle));
        line.setText(context.getResources().getString(R.string.line_chart));
        lineLayout.addView(line);
    }
}
