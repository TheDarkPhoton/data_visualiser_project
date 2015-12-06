package com.darkphoton.data_visualiser_project.data.processed.indicators.negative;

import com.darkphoton.data_visualiser_project.data.processed.indicators.NegativeIndicator;
import com.darkphoton.data_visualiser_project.data.raw.RIndicator;

public class PollutionMeanAnnualExposure extends NegativeIndicator {
    public final static String id = "EN.ATM.PM25.MC.M3";
    public final static String name = "Pollution Mean Annual Exposure";
    public final static String title = "PM2.5 air pollution, mean annual exposure (micrograms per cubic meter)";

    public PollutionMeanAnnualExposure(RIndicator indicator) {
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
