package com.darkphoton.data_visualiser_project.data.processed.indicators.positive;

import com.darkphoton.data_visualiser_project.data.processed.indicators.PositiveIndicator;
import com.darkphoton.data_visualiser_project.data.raw.RIndicator;

import java.io.Serializable;

public class RenewableInternalFreshwater extends PositiveIndicator {
    public final static String id = "ER.H2O.INTR.PC";
    public final static String name = "Renewable Internal Freshwater";
    public final static String title = "Renewable Internal Freshwater per Capita (cubic meters)";
    public final static String description ="Renewable internal freshwater resources flows refer to internal renewable resources (internal river flows and groundwater from rainfall) in the country. Renewable internal freshwater resources per capita are calculated using the World Bank's population estimates.";

    public RenewableInternalFreshwater(RIndicator indicator){
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
