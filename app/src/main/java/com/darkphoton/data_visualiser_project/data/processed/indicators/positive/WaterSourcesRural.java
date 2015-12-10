package com.darkphoton.data_visualiser_project.data.processed.indicators.positive;

import com.darkphoton.data_visualiser_project.data.processed.indicators.PositiveIndicator;
import com.darkphoton.data_visualiser_project.data.raw.RIndicator;

import java.io.Serializable;

public class WaterSourcesRural extends PositiveIndicator {
    public final static String id = "SH.H2O.SAFE.RU.ZS";
    public final static String name = "Improved Water Sources - Rural";
    public final static String title = "Improved Water Sources - Rural (% of population with access)";
    public final static String description ="Access to an improved water source refers to the percentage of the population using an improved drinking water source. The improved drinking water source includes piped water on premises (piped household water connection located inside the user’s dwelling, plot or yard), and other improved drinking water sources (public taps or standpipes, tube wells or boreholes, protected dug wells, protected springs, and rainwater collection).";

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

    public String getDescription() {
        return description;
    }
}
