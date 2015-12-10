package com.darkphoton.data_visualiser_project;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.darkphoton.data_visualiser_project.data.processed.PCountry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by darkphoton on 10/12/15.
 */
public class CountryItem extends RelativeLayout {
    public static final List<Integer> colours;
    public static final List<Integer> transparent_colours;

    static {
        List<Integer> c = new ArrayList<>();
        c.add(0xffffd700);                              //Gold
        c.add(0xffc0c0c0);                              //Silver
        c.add(0xffcd7f32);                              //Bronze
        c.add(0xffA4CDE7);                              //Light Blue
        c.add(0xffe5e4e2);                              //Light Grey
        colours = Collections.unmodifiableList(c);

        //transparent versions
        List<Integer> t = new ArrayList<>();
        t.add(0x77ffd700);
        t.add(0x77c0c0c0);
        t.add(0x77cd7f32);
        t.add(0x77add8e6);
        t.add(0x77e5e4e2);
        transparent_colours = Collections.unmodifiableList(t);
    }

    private Point size = MainActivity.screen_size;

    public CountryItem(Context context, PCountry country, int pos) {
        super(context);

        setLayoutParams(new LinearLayout.LayoutParams(size.x, LinearLayout.LayoutParams.MATCH_PARENT));

        TextView place = new TextView(context);
        place.setLayoutParams(new ViewGroup.LayoutParams(size.x / 2, size.y / 2));
        place.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
        place.setText(pos + 1 + "");
        place.setTypeface(null, Typeface.BOLD);
        place.setTextSize(size.y / 10);
        addView(place);

        if (pos < 4)
            place.setBackgroundColor(colours.get(pos));
        else
            place.setBackgroundColor(colours.get(3));

        TextView name = new TextView(context);
        name.setLayoutParams(new ViewGroup.LayoutParams(size.x / 2, size.y / 2));
        name.setGravity(Gravity.CENTER_HORIZONTAL);
        name.setText(country.getName());
        name.setTextSize(size.y / 25);
        name.setY(size.y / 2);
        addView(name);

        if (pos < 4)
            name.setBackgroundColor(transparent_colours.get(pos));
        else
            name.setBackgroundColor(transparent_colours.get(3));

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
