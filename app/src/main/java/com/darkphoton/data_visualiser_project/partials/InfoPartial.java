package com.darkphoton.data_visualiser_project.partials;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;

import com.darkphoton.data_visualiser_project.MainActivity;

/**
 * Created by darkphoton on 10/12/15.
 */
public class InfoPartial extends PartialPanel {
    private Point size = MainActivity.screen_size;

    public InfoPartial(Context context) {
        super(context);

        setLayoutParams(new LayoutParams(size.x / 2, size.y));
        setBackgroundColor(Color.RED);
        setX(size.x);
    }

    public void open(){
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
