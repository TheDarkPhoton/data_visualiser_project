package com.darkphoton.data_visualiser_project.data.processed.indicators.positive;

import com.darkphoton.data_visualiser_project.data.processed.indicators.PositiveIndicator;
import com.darkphoton.data_visualiser_project.data.raw.RIndicator;

import java.io.Serializable;

public class RailwayPassengersCarried extends PositiveIndicator {
    public final static String id = "IS.RRS.PASG.KM";
    public final static String name = "Railway Passengers Carried";
    public final static String title = "Railways, passengers carried (million passenger-km)";
    public final static String description = "Passengers carried by railway are the number of passengers transported by rail times kilometers traveled.";

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

    public String getDescription() {
        return description;
    }
}
