package com.darkphoton.data_visualiser_project.partials;

import android.content.Context;
import android.graphics.Point;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.darkphoton.data_visualiser_project.MainActivity;
import com.darkphoton.data_visualiser_project.data.Processor;
import com.darkphoton.data_visualiser_project.data.processed.PCountry;
import com.darkphoton.data_visualiser_project.data.processed.PIndicator;

/**
 * Created by darkphoton on 10/12/15.
 */
public class IndicatorList extends LinearLayout {
    private Point size = MainActivity.screen_size;

    public IndicatorList(Context context, Processor data, PCountry country) {
        super(context);

        setLayoutParams(new ViewGroup.LayoutParams(size.x / 2, ViewGroup.LayoutParams.WRAP_CONTENT));
        setOrientation(LinearLayout.VERTICAL);

        for (PIndicator indicator : country.getIndicators().values()) {
            addView(new IndicatorItem(context, data, country, indicator));
        }
    }
}
