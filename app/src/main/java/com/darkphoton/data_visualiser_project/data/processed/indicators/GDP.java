package com.darkphoton.data_visualiser_project.data.processed.indicators;

import com.darkphoton.data_visualiser_project.data.processed.PIndicator;
import com.darkphoton.data_visualiser_project.data.raw.RIndicator;
import com.darkphoton.data_visualiser_project.data.raw.RData;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class GDP extends PIndicator {
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