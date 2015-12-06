package com.darkphoton.data_visualiser_project.data.processed.indicators.positive;

import com.darkphoton.data_visualiser_project.data.processed.indicators.PositiveIndicator;
import com.darkphoton.data_visualiser_project.data.raw.RIndicator;

public class ElectricityAccessRural extends PositiveIndicator {
    public final static String id = "EG.ELC.ACCS.RU.ZS";
    public final static String name = "Electricity Access - Rural";
    public final static String title = "Electricity Access - Rural (% of population)";

    public ElectricityAccessRural(RIndicator indicator){
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
