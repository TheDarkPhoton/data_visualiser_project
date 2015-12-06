package com.darkphoton.data_visualiser_project.data.processed.indicators.positive;

import com.darkphoton.data_visualiser_project.data.processed.indicators.PositiveIndicator;
import com.darkphoton.data_visualiser_project.data.raw.RIndicator;

public class SanitationFacilitiesRural extends PositiveIndicator {
    public final static String id = "SH.STA.ACSN.RU";
    public final static String name = "Improved Sanitation Facilities - Rural";
    public final static String title = "Improved Sanitation Facilities - Rural (% of population with access)";

    public SanitationFacilitiesRural(RIndicator indicator){
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
