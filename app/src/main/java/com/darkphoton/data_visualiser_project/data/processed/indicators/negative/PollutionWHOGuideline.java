package com.darkphoton.data_visualiser_project.data.processed.indicators.negative;

import com.darkphoton.data_visualiser_project.data.processed.indicators.NegativeIndicator;
import com.darkphoton.data_visualiser_project.data.raw.RIndicator;

public class PollutionWHOGuideline extends NegativeIndicator {
    public final static String id = "EN.ATM.PM25.MC.ZS";
    public final static String name = "Pollution WHO Guideline";
    public final static String title = "PM2.5 air pollution, population exposed to levels exceeding WHO guideline value (% of total)";

    public PollutionWHOGuideline(RIndicator indicator) {
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
