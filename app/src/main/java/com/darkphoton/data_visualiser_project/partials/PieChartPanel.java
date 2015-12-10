package com.darkphoton.data_visualiser_project.partials;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.util.Pair;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;

import com.darkphoton.data_visualiser_project.MainActivity;

import java.util.ArrayList;

/**
 * Created by darkphoton on 10/12/15.
 */
public class PieChartPanel extends PartialPanel {
    private Point size = MainActivity.screen_size;

    public PieChartPanel(Context context) {
        super(context);

        setLayoutParams(new LayoutParams(size.x, size.y));
        setBackgroundColor(Color.GREEN);
        setY(-size.y);
    }

    public void open(ArrayList<Pair<String, Double>> data){
        setY(-size.y);
        TranslateAnimation animation = new TranslateAnimation(0, 0, 0, size.y);
        animation.setInterpolator(new DecelerateInterpolator(1.5f));
        animation.setDuration(500);
        animation.setFillAfter(true);
        this.startAnimation(animation);
    }

    public void close(){
        setY(0);
        TranslateAnimation animation = new TranslateAnimation(0, 0, 0, -size.y);
        animation.setInterpolator(new DecelerateInterpolator(1.5f));
        animation.setDuration(500);
        animation.setFillAfter(true);
        startAnimation(animation);
    }
}
