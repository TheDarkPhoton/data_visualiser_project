package com.darkphoton.data_visualiser_project.data.processed.indicators;

import com.darkphoton.data_visualiser_project.data.processed.PIndicator;
import com.darkphoton.data_visualiser_project.data.raw.RIndicator;

public class SanitationFacilities extends PIndicator {
    public final static String id = "SH.STA.ACSN";
    public final static String name = "Improved Sanitation Facilities";
    public final static String title = "Improved Sanitation Facilities (% of population with access)";

    public SanitationFacilities(RIndicator indicator){
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
