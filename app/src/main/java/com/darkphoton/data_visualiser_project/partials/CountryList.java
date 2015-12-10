package com.darkphoton.data_visualiser_project.partials;

import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.darkphoton.data_visualiser_project.MainActivity;
import com.darkphoton.data_visualiser_project.R;
import com.darkphoton.data_visualiser_project.data.Processor;

/**
 * Created by darkphoton on 10/12/15.
 */
public class CountryList extends LinearLayout {
    private Point size = MainActivity.screen_size;

    public CountryList(Context context) {
        super(context);
        final Drawable img = ContextCompat.getDrawable(context, R.drawable.helpmenu);
//        setLayoutParams(new ViewGroup.LayoutParams(size.x, size.y));
        setOrientation(LinearLayout.HORIZONTAL);
        ViewGroup background = new ViewGroup(context) {
            @Override
            protected void onLayout(boolean changed, int l, int t, int r, int b) {
                setBackground(img);
            }
        };
        background.setLayoutParams(new ViewGroup.LayoutParams(size.x, size.y));
        addView(background);
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
