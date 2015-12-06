package com.darkphoton.data_visualiser_project.data.processed.indicators.positive;

import com.darkphoton.data_visualiser_project.data.processed.indicators.PositiveIndicator;
import com.darkphoton.data_visualiser_project.data.raw.RIndicator;

public class GDPPerCapita extends PositiveIndicator {
    public final static String id = "NY.GDP.PCAP.CD";
    public final static String name = "GDP Per Capita";
    public final static String title = "GDP per capita (current US$)";

    public GDPPerCapita(RIndicator indicator){
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
