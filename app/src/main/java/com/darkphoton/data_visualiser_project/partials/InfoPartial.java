package com.darkphoton.data_visualiser_project.partials;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.darkphoton.data_visualiser_project.MainActivity;
import com.darkphoton.data_visualiser_project.data.processed.PIndicator;

/**
 * Created by darkphoton on 10/12/15.
 */
public class InfoPartial extends PartialPanel {
    private Point size = MainActivity.screen_size;
    private TextView title;
    private TextView description;

    public InfoPartial(Context context) {
        super(context);

        setLayoutParams(new LayoutParams(size.x / 2, size.y));
        setBackgroundColor(Color.WHITE);
        setX(size.x);

        title = new TextView(context);
        title.setId(View.generateViewId());
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(size.x / 2, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(25, 25, 25, 0);
        title.setLayoutParams(params);
        title.setTextSize(size.y / 50);
        title.setTypeface(null, Typeface.BOLD);
        addView(title);
        title.setBackgroundColor(CountryItem.transparent_colours.get(4));

        params = new RelativeLayout.LayoutParams(size.x / 2, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.BELOW, title.getId());
        params.setMargins(25, 25, 25, 25);
        description = new TextView(context);
        description.setLayoutParams(params);
        description.setTextSize(size.y / 70);
        description.setY(title.getHeight());
        addView(description);
    }

    public void open(PIndicator indicator){
        title.setText(indicator.getTitle());
        description.setText(indicator.getDescription());

        setX(size.x);
        TranslateAnimation animation = new TranslateAnimation(0, (size.x / 2) - size.x, 0, 0);
        animation.setInterpolator(new DecelerateInterpolator(1.5f));
        animation.setDuration(500);
        animation.setFillAfter(true);
        this.startAnimation(animation);
    }

    public void close(){
        setX(size.x / 2);
        TranslateAnimation animation = new TranslateAnimation(0, size.x - (size.x / 2), 0, 0);
        animation.setInterpolator(new DecelerateInterpolator(1.5f));
        animation.setDuration(500);
        animation.setFillAfter(true);
        startAnimation(animation);
    }
}
