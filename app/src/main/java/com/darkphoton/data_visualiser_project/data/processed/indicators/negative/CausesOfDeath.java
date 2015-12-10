package com.darkphoton.data_visualiser_project.data.processed.indicators.negative;

import com.darkphoton.data_visualiser_project.data.processed.indicators.NegativeIndicator;
import com.darkphoton.data_visualiser_project.data.raw.RIndicator;

import java.io.Serializable;

public class CausesOfDeath extends NegativeIndicator {
    public final static String id = "SH.DTH.COMM.ZS";
    public final static String name = "Causes of Death";
    public final static String title = "Cause of death, by communicable diseases and maternal, prenatal and nutrition conditions (% of total)";
    public final static String description = "Cause of death refers to the share of all deaths for all ages by underlying causes. Communicable diseases and maternal, prenatal and nutrition conditions include infectious and parasitic diseases, respiratory infections, and nutritional deficiencies such as underweight and stunting.";

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

    public String getDescription() {
        return description;
    }
}
