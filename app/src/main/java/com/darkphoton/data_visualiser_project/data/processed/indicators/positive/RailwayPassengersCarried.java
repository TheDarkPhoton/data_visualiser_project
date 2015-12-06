package com.darkphoton.data_visualiser_project.data.processed.indicators.positive;

import com.darkphoton.data_visualiser_project.data.processed.indicators.PositiveIndicator;
import com.darkphoton.data_visualiser_project.data.raw.RIndicator;

public class RailwayPassengersCarried extends PositiveIndicator {
    public final static String id = "IS.RRS.PASG.KM";
    public final static String name = "Railway Passengers Carried";
    public final static String title = "Railways, passengers carried (million passenger-km)";

    public RailwayPassengersCarried(RIndicator indicator){
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