package com.darkphoton.data_visualiser_project.data.processed.indicators;

import com.darkphoton.data_visualiser_project.data.processed.PIndicator;
import com.darkphoton.data_visualiser_project.data.raw.RIndicator;
import com.darkphoton.data_visualiser_project.sidebar.SideBarItemAdapter;

public abstract class PositiveIndicator extends PIndicator {
    public PositiveIndicator(RIndicator indicator){
        super(indicator);
    }

    public void normalize(double highest, double lowest){
        if (Double.isNaN(_average))
            _normalized = 0;
        else
            _normalized = (_average - lowest) / (highest - lowest);

        _normalized *= SideBarItemAdapter.sliders.get(getId()) / 100;
    }
}
