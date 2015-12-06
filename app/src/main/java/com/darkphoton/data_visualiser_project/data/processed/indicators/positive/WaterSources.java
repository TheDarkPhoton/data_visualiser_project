package com.darkphoton.data_visualiser_project.data.processed.indicators.positive;

import com.darkphoton.data_visualiser_project.data.processed.indicators.PositiveIndicator;
import com.darkphoton.data_visualiser_project.data.raw.RIndicator;

public class WaterSources extends PositiveIndicator {
    public final static String id = "SH.H2O.SAFE.ZS";
    public final static String name = "Improved Water Sources";
    public final static String title = "Improved Water Sources (% of population with access)";

    public WaterSources(RIndicator indicator){
        super(indicator);
    }

    public String getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getTitle(){
        return title;
    }
}
