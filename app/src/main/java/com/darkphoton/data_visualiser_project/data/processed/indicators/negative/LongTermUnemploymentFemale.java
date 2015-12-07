package com.darkphoton.data_visualiser_project.data.processed.indicators.negative;

import com.darkphoton.data_visualiser_project.data.processed.indicators.NegativeIndicator;
import com.darkphoton.data_visualiser_project.data.raw.RIndicator;

public class LongTermUnemploymentFemale extends NegativeIndicator {
    public final static String id = "SL.UEM.LTRM.FE.ZS";
    public final static String name = "Long Term Unemployment - Female";
    public final static String title = "Long-term unemployment, female (% of female unemployment) ";
    public final static String description = "Long-term unemployment refers to the number of people with continuous periods of unemployment extending for a year or longer, expressed as a percentage of the total unemployed.";

    public LongTermUnemploymentFemale(RIndicator indicator) {
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
