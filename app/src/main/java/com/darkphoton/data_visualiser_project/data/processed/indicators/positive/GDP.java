package com.darkphoton.data_visualiser_project.data.processed.indicators.positive;

import com.darkphoton.data_visualiser_project.data.processed.indicators.PositiveIndicator;
import com.darkphoton.data_visualiser_project.data.raw.RIndicator;

public class GDP extends PositiveIndicator {
    public final static String id = "NY.GDP.MKTP.CD";
    public final static String name = "GDP";
    public final static String title = "Gross Domestic Product (in US$)";

    public GDP(RIndicator indicator){
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