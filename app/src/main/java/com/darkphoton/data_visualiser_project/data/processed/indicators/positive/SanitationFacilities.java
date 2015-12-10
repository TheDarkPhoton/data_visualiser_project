package com.darkphoton.data_visualiser_project.data.processed.indicators.positive;

import com.darkphoton.data_visualiser_project.data.processed.indicators.PositiveIndicator;
import com.darkphoton.data_visualiser_project.data.raw.RIndicator;

import java.io.Serializable;

public class SanitationFacilities extends PositiveIndicator {
    public final static String id = "SH.STA.ACSN";
    public final static String name = "Improved Sanitation Facilities";
    public final static String title = "Improved Sanitation Facilities (% of population with access)";
    public final static String description = "Access to improved sanitation facilities refers to the percentage of the population using improved sanitation facilities. Improved sanitation facilities are likely to ensure hygienic separation of human excreta from human contact. They include flush/pour flush (to piped sewer system, septic tank, pit latrine), ventilated improved pit (VIP) latrine, pit latrine with slab, and composting toilet.";

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

    public String getDescription() {
        return description;
    }
}
