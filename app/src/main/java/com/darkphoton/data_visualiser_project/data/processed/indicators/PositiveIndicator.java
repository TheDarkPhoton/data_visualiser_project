package com.darkphoton.data_visualiser_project.data.processed.indicators;

import com.darkphoton.data_visualiser_project.data.processed.PIndicator;
import com.darkphoton.data_visualiser_project.data.raw.RIndicator;

public abstract class PositiveIndicator extends PIndicator {
    public PositiveIndicator(RIndicator indicator){
        super(indicator);
    }

    public void normalize(double highest, double lowest){
        _normalized = (_average - lowest) / (highest - lowest);
    }
}
