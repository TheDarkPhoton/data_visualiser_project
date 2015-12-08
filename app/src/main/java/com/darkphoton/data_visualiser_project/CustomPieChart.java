package com.darkphoton.data_visualiser_project;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;

public class CustomPieChart extends View {
    public CustomPieChart(Context context) {
        super(context);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int dimensions = getWidth();
        if (getHeight() < dimensions)
            dimensions = getHeight();

        int outerRadius = (int)((dimensions / 2) * 0.8f);
        int innerRadius = (int)((dimensions / 2) * 0.5f);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
//        paint.setColor(Color.parseColor("#CD5C5C"));
        paint.setColor(Color.parseColor("#000000"));

        canvas.drawCircle(getWidth()/2, getHeight()/2, outerRadius, paint);
        canvas.drawCircle(getWidth()/2, getHeight()/2, innerRadius, paint);
    }
}
