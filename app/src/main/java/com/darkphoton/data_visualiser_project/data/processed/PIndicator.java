package com.darkphoton.data_visualiser_project.data.processed;

import com.darkphoton.data_visualiser_project.data.processed.indicators.CountryGDP;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class PIndicator {
    public final static Map<String, Class> indicatorClasses;

    static {
        HashMap<String, Class> map = new HashMap<>();
        map.put("NY.GDP.MKTP.CD", CountryGDP.class);

        indicatorClasses = Collections.unmodifiableMap(map);
    }

    protected double _average = 0;
    protected double _normalized = 0;

    public void normalize(double highest){
        _normalized = _average / highest;
    }

    public double getAverage(){
        return _average;
    }

    public double getNormalizedAverage(){
        return _normalized;
    }

    public abstract String getId();
    public abstract String getName();
    public abstract String getTitle();
}
