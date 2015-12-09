package com.darkphoton.data_visualiser_project;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.v4.view.VelocityTrackerCompat;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.Log;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.OnTouchListener;

import com.darkphoton.data_visualiser_project.data.Processor;
import com.darkphoton.data_visualiser_project.data.processed.PCountry;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CustomDrawer extends View implements OnTouchListener {
    private List<PCountry> _countries = new ArrayList<>();
    private List<Float> _fractions = new ArrayList<>();
    private List<Paint> _randomPaints = new ArrayList<>();

    private Typeface _typeface;

    private Canvas _back;
    private Bitmap _bitmap;
    private Paint _redPaint = new Paint();
    private Paint _lightGreyPaint = new Paint();
    private Paint _strokePaint = new Paint();
    private Paint _transparentPaint = new Paint();

    private Paint _numberPaint = new Paint();
    private Paint _headerPaint = new Paint();

    private int _current_index = 0;
    private int _new_index = 0;

    private String _current = "ABCDEFGHIJ"; //10

    public CustomDrawer(Context context) {
        super(context);

        _typeface = Typeface.createFromAsset(context.getAssets(), "geobats.ttf");

        init();

        setOnTouchListener(this);
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
    }

    private void init(){
        _redPaint.setColor(Color.parseColor("#CD5C5C"));
        _transparentPaint.setColor(getResources().getColor(android.R.color.transparent));
        _transparentPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));

        Random r = new Random();

        Paint p = new Paint();
        p.setColor(Color.parseColor("#FFD700"));
        _randomPaints.add(p);

        p = new Paint();
        p.setColor(Color.parseColor("#C0C0C0"));
        _randomPaints.add(p);

        p = new Paint();
        p.setColor(Color.parseColor("#CD7F32"));
        _randomPaints.add(p);

        _lightGreyPaint = new Paint();
        _lightGreyPaint.setColor(Color.parseColor("#E5E4E2"));

        _strokePaint.setColor(Color.parseColor("#000000"));
        _strokePaint.setStyle(Paint.Style.STROKE);
        _strokePaint.setAntiAlias(true);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        _bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        _back = new Canvas(_bitmap);

        _strokePaint.setStrokeWidth(5);
        _numberPaint.setTextSize(300);
        _headerPaint.setTextSize(100);
    }

    int _y = 0;
    int _x = 0;
    private static final String DEBUG_TAG = "Velocity";
    private VelocityTracker mVelocityTracker = null;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int index = event.getActionIndex();
        int action = event.getActionMasked();
        int pointerId = event.getPointerId(index);

        switch(action) {
            case MotionEvent.ACTION_DOWN:
                if (event.getX() > getWidth() - 250 && event.getX() < getWidth() - 50 ){
                    for (int i = 0; i < _countries.size(); i++) {
                        if (event.getY() > 50 + 250 * i && event.getY() < 250 + 250 * i){
                            _new_index = i;
                            animateY(i);
                        }
                    }
                }

                if(mVelocityTracker == null) {
                    // Retrieve a new VelocityTracker object to watch the velocity of a motion.
                    mVelocityTracker = VelocityTracker.obtain();
                }
                else {
                    // Reset the velocity tracker back to its initial state.
                    mVelocityTracker.clear();
                }
                // Add a user's movement to the tracker.
                mVelocityTracker.addMovement(event);
                break;
            case MotionEvent.ACTION_MOVE:
                mVelocityTracker.addMovement(event);
                // When you want to determine the velocity, call
                // computeCurrentVelocity(). Then call getXVelocity()
                // and getYVelocity() to retrieve the velocity for each pointer ID.
                mVelocityTracker.computeCurrentVelocity(1000);
                // Log velocity of pixels per second
                // Best practice to use VelocityTrackerCompat where possible.
                Log.d("", "X velocity: " +
                        VelocityTrackerCompat.getXVelocity(mVelocityTracker,
                                pointerId));
                Log.d("", "Y velocity: " +
                        VelocityTrackerCompat.getYVelocity(mVelocityTracker,
                                pointerId));

//                _y += -(int)(VelocityTrackerCompat.getYVelocity(mVelocityTracker, pointerId) / 25);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                // Return a VelocityTracker object back to be re-used by others.
                mVelocityTracker.recycle();
                mVelocityTracker = null;

                break;
        }

        return true;
    }


    float startY = 0;
    float endY = 0;
    float integral = 1;

    private void animateY(int index){
        startY = _y;
        endY = getHeight() * index;

        if (_current_index < index){
            integral = (endY - startY) * 0.05f;
        } else if (index < _current_index) {
            integral = (endY - startY) * 0.05f;
        }
    }

    private final Rect textBounds = new Rect();
    public void drawTextCentred(Canvas canvas, Paint paint, String text, float cx, float cy) {
        paint.getTextBounds(text, 0, text.length(), textBounds);
        canvas.drawText(text, cx - textBounds.exactCenterX(), cy - textBounds.exactCenterY(), paint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (Math.abs(_y - endY) > 5){
            _y += integral;
        } else {
            _current_index = _new_index;
        }

        if (_y < 0) _y = 0;
        if (_y > getHeight() * (_countries.size() - 1)) _y = getHeight() * (_countries.size() - 1);

        _back.drawRect(0, 0, getWidth(), getHeight(), _transparentPaint);


        for (int i = 0; i < _countries.size(); i++) {
            Paint p = _lightGreyPaint;
            if (i < 3)
                p = _randomPaints.get(i);

            int offsetX = getWidth() / 3;
            int offsetY = (getHeight() / 4) + (getHeight() * i) - _y;

            _back.drawCircle(offsetX, offsetY, _numberPaint.getTextSize(), p);
            _back.drawCircle(offsetX, offsetY, _numberPaint.getTextSize(), _strokePaint);
            drawTextCentred(_back, _numberPaint, (i + 1) + "", offsetX, offsetY);

            TextPaint txtPaint = new TextPaint(Color.BLACK);
            txtPaint.setTextSize(100);

            StaticLayout layout = new StaticLayout(
                    _countries.get(i).getName(),
                    txtPaint,
                    getWidth() - (getWidth() / 3),
                    Layout.Alignment.ALIGN_CENTER,
                    1.0f, 0.0f, false);

            _back.translate(0, offsetY + _numberPaint.getTextSize());
            layout.draw(_back);
            _back.translate(0, -(offsetY + _numberPaint.getTextSize()));
        }

        for (int i = 0; i < _countries.size(); i++) {
            Paint p = _lightGreyPaint;
            if (i < 3)
                p = _randomPaints.get(i);

            int x = getWidth() - 150;
            int y = 150 + 250 * i;

            _back.drawCircle(x, y, 100, p);
            _back.drawCircle(x, y, 100, _strokePaint);
            drawTextCentred(_back, _headerPaint, (i + 1) + "", x, y);
        }

        canvas.drawBitmap(_bitmap, 0, 0, null);
        invalidate();
    }
}
