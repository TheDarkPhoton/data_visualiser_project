package com.darkphoton.data_visualiser_project.data.processed.indicators.positive;

import com.darkphoton.data_visualiser_project.data.processed.indicators.PositiveIndicator;
import com.darkphoton.data_visualiser_project.data.raw.RIndicator;

public class TotalReserves extends PositiveIndicator {
    public final static String id = "FI.RES.TOTL.CD";
    public final static String name = "Total Reserves";
    public final static String title = "Total reserves (includes gold, current US$)";

    public TotalReserves(RIndicator indicator){
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
