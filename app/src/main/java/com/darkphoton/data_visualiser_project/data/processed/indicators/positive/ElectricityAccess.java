package com.darkphoton.data_visualiser_project.data.processed.indicators.positive;

import com.darkphoton.data_visualiser_project.data.processed.indicators.PositiveIndicator;
import com.darkphoton.data_visualiser_project.data.raw.RIndicator;

public class ElectricityAccess extends PositiveIndicator {
    public final static String id = "EG.ELC.ACCS.ZS";
    public final static String name = "Electricity Access";
    public final static String title = "Electricity Access (% of population)";
    public final static String description = "Access to electricity is the percentage of population with access to electricity. Electrification data are collected from industry, national surveys and international sources.";

    public ElectricityAccess(RIndicator indicator){
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
