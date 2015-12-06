package com.darkphoton.data_visualiser_project.data.processed.indicators.negative;

import com.darkphoton.data_visualiser_project.data.processed.indicators.NegativeIndicator;
import com.darkphoton.data_visualiser_project.data.raw.RIndicator;

public class PollutionDensity extends NegativeIndicator {
    public final static String id = "EN.POP.DNST";
    public final static String name = "Pollution Density";
    public final static String title = "Pollution Density (people per sq. km of land area)";

    public PollutionDensity(RIndicator indicator){
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
