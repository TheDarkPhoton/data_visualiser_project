package com.darkphoton.data_visualiser_project;

import android.content.Context;
import android.graphics.Point;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.darkphoton.data_visualiser_project.data.Processor;
import com.darkphoton.data_visualiser_project.data.processed.PCountry;

/**
 * Created by darkphoton on 10/12/15.
 */
public class CountryList extends LinearLayout {
    private Point size = MainActivity.screen_size;

    public CountryList(Context context) {
        super(context);

        setLayoutParams(new ViewGroup.LayoutParams(size.x, size.y));
        setOrientation(LinearLayout.HORIZONTAL);
    }

    public CountryList(Context context, Processor data) {
        super(context);

        setLayoutParams(new ViewGroup.LayoutParams(size.x, size.y));
        setOrientation(LinearLayout.HORIZONTAL);

        for (int i = 0; i < data.getCountries().size(); i++) {
            addView(new CountryItem(context, data.getCountries().get(i), i));
        }
    }
}
