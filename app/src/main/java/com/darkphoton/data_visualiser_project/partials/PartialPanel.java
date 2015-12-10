package com.darkphoton.data_visualiser_project.partials;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Created by darkphoton on 10/12/15.
 */
public abstract class PartialPanel extends RelativeLayout {
    protected boolean _closed = true;

    public PartialPanel(Context context) {
        super(context);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return _closed || super.onInterceptTouchEvent(ev);
    }

    public abstract void close();
}
