package com.darkphoton.data_visualiser_project;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Typeface;
import android.view.View;

import com.darkphoton.data_visualiser_project.data.Processor;
import com.darkphoton.data_visualiser_project.data.processed.PCountry;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CustomPieChart extends View implements View.OnClickListener {
    private List<PCountry> _countries;
    private List<Float> _fractions = new ArrayList<>();
    private List<Paint> _randomPaints = new ArrayList<>();

    private Typeface _typeface;

    private Canvas _back;
    private Bitmap _bitmap;
    private Paint _redPaint = new Paint();
    private Paint _strokePaint = new Paint();
    private Paint _transparentPaint = new Paint();
    private Paint _countriesPaint = new Paint();

    public CustomPieChart(Context context) {
        super(context);

        _typeface = Typeface.createFromAsset(context.getAssets(), "geobats.ttf");

        init();

        setOnClickListener(this);
    }

    public void updateData(Processor data){
        _countries = data.getCountries();

        double total = 0;
        for (PCountry country : _countries) {
            total += country.getValue();
        }

        _fractions = new ArrayList<>();
        for (PCountry country : _countries) {
            _fractions.add((float)(country.getValue() / total * 360));
        }
        invalidate();
    }

    private void init(){
        _redPaint.setColor(Color.parseColor("#CD5C5C"));
        _transparentPaint.setColor(getResources().getColor(android.R.color.transparent));
        _transparentPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));

        Random r = new Random();
        for (int i = 0; i < 5; i++) {
            Paint p = new Paint();
            int red = r.nextInt(255);
            int green = r.nextInt(255);
            int blue = r. nextInt(255);
            p.setColor(Color.rgb(red, green, blue));
            _randomPaints.add(p);
        }

        _strokePaint.setStrokeWidth(5);
        _strokePaint.setColor(Color.parseColor("#000000"));
        _strokePaint.setStyle(Paint.Style.STROKE);
        _strokePaint.setAntiAlias(true);

        _countriesPaint.setTypeface(_typeface);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        _bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        _back = new Canvas(_bitmap);

        int dimensions = getWidth();
        if (getHeight() < dimensions)
            dimensions = getHeight();

        int outerRadius = (int)((dimensions / 2) * 0.6f);
        int innerRadius = (int)((dimensions / 2) * 0.3f);

//        _back.drawCircle(getWidth() / 2, getHeight() / 2, outerRadius, _redPaint);
//        _back.drawCircle(getWidth()/2, getHeight()/2, outerRadius, _strokePaint);

        Path path = new Path();

//        path.lineTo(getWidth(), getHeight() / 2);
//        path.lineTo(getWidth(), getHeight());

        float startAngle = -90;
        int count = 0;
        for (float fraction : _fractions) {
            path.moveTo((getWidth() / 2), (getHeight() / 2));
            path.arcTo(
                    (getWidth() / 2) - outerRadius,
                    (getHeight() / 2) - outerRadius,
                    (getWidth() / 2) + outerRadius,
                    (getHeight() / 2) + outerRadius, startAngle, fraction, false);
//            path.close();
            _back.drawPath(path, _randomPaints.get(count++));
            _back.drawPath(path, _strokePaint);

            startAngle += fraction;
        }

        _back.drawCircle(getWidth() / 2, getHeight() / 2, innerRadius, _transparentPaint);
        _back.drawCircle(getWidth()/2, getHeight()/2, innerRadius, _strokePaint);
        _back.drawText("A", getWidth() / 2, getHeight() / 2, _countriesPaint);

        canvas.drawBitmap(_bitmap, 0, 0, null);
    }
}
