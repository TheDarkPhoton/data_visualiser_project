package com.darkphoton.data_visualiser_project.data.processed.indicators.positive;

import com.darkphoton.data_visualiser_project.data.processed.indicators.PositiveIndicator;
import com.darkphoton.data_visualiser_project.data.raw.RIndicator;

public class ElectricityAccess extends PositiveIndicator {
    public final static String id = "EG.ELC.ACCS.ZS";
    public final static String name = "Electricity Access";
    public final static String title = "Electricity Access (% of population)";

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
}
