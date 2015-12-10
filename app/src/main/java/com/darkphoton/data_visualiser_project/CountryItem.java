package com.darkphoton.data_visualiser_project;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.darkphoton.data_visualiser_project.data.processed.PCountry;

/**
 * Created by darkphoton on 10/12/15.
 */
public class CountryItem extends RelativeLayout {
    private Point size = MainActivity.screen_size;

    public CountryItem(Context context, PCountry country, int pos) {
        super(context);

        setLayoutParams(new LinearLayout.LayoutParams(size.x, LinearLayout.LayoutParams.MATCH_PARENT));

        TextView txt1 = new TextView(context);
        txt1.setLayoutParams(new ViewGroup.LayoutParams(size.x / 2, size.y / 2));
        txt1.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
        txt1.setText(pos + 1 + "");
        txt1.setTextSize(size.y / 10);
        addView(txt1);

        txt1 = new TextView(context);
        txt1.setLayoutParams(new ViewGroup.LayoutParams(size.x / 2, size.y / 2));
        txt1.setGravity(Gravity.CENTER_HORIZONTAL);
        txt1.setText(country.getName());
        txt1.setTextSize(size.y / 25);
        txt1.setY(size.y / 2);
        addView(txt1);

        final ScrollView verticalScroll = new ScrollView(context);
        verticalScroll.setLayoutParams(new ViewGroup.LayoutParams(size.x / 2, size.y));
        verticalScroll.setX(size.x / 2);
        addView(verticalScroll);

        verticalScroll.addView(new IndicatorList(context, country));

//        verticalScroll.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                verticalScroll.scrollTo(0, txt3.getTop());
//            }
//        }, 1000);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }
}
