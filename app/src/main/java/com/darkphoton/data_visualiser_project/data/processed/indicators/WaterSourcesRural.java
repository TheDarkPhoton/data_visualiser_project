package com.darkphoton.data_visualiser_project.data.processed.indicators;

import com.darkphoton.data_visualiser_project.data.processed.PIndicator;
import com.darkphoton.data_visualiser_project.data.raw.RIndicator;

public class WaterSourcesRural extends PIndicator {
    public final static String id = "SH.H2O.SAFE.RU.ZS";
    public final static String name = "Improved Water Sources - Rural";
    public final static String title = "Improved Water Sources - Rural (% of population with access)";

    public WaterSourcesRural(RIndicator indicator){
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
