package com.darkphoton.data_visualiser_project.data.processed.indicators;

import com.darkphoton.data_visualiser_project.data.processed.PIndicator;
import com.darkphoton.data_visualiser_project.data.raw.RIndicator;

public class SanitationFacilitiesUrban extends PIndicator {
    public final static String id = "SH.STA.ACSN.UR";
    public final static String name = "Improved Sanitation Facilities - Urban";
    public final static String title = "Improved Sanitation Facilities - Urban (% of population with access)";

    public SanitationFacilitiesUrban(RIndicator indicator){
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
