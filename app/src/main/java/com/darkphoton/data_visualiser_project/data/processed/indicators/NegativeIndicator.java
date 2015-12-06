package com.darkphoton.data_visualiser_project.data.processed.indicators;

import com.darkphoton.data_visualiser_project.data.processed.PIndicator;
import com.darkphoton.data_visualiser_project.data.raw.RIndicator;

public abstract class NegativeIndicator extends PIndicator {
    public NegativeIndicator(RIndicator indicator){
        super(indicator);
    }

    public void normalize(double highest, double lowest){
        _normalized = -( ((-_average) - (-highest)) / ((-highest) - (-lowest)) );
    }
}
