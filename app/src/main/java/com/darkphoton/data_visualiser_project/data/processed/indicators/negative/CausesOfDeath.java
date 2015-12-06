package com.darkphoton.data_visualiser_project.data.processed.indicators.negative;

import com.darkphoton.data_visualiser_project.data.processed.indicators.NegativeIndicator;
import com.darkphoton.data_visualiser_project.data.raw.RIndicator;

public class CausesOfDeath extends NegativeIndicator {
    public final static String id = "SH.DTH.COMM.ZS";
    public final static String name = "Causes of Death";
    public final static String title = "Cause of death, by communicable diseases and maternal, prenatal and nutrition conditions (% of total)";

    public CausesOfDeath(RIndicator indicator) {
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
