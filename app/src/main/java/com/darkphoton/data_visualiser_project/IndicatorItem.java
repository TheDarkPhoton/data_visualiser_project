package com.darkphoton.data_visualiser_project;

import android.content.Context;
import android.graphics.Point;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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

        setLayoutParams(new LinearLayout.LayoutParams(size.x / 2, size.y));

        TextView txt2 = new TextView(context);
        txt2.setLayoutParams(new ViewGroup.LayoutParams(size.x / 2, size.y));
        txt2.setText(indicator.getAverage() + "");
        txt2.setTextSize(size.y / 40);
        addView(txt2);
    }
}
