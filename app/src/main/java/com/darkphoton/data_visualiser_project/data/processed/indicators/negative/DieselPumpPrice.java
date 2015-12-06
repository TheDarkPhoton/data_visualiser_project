package com.darkphoton.data_visualiser_project.data.processed.indicators.negative;

import com.darkphoton.data_visualiser_project.data.processed.indicators.NegativeIndicator;
import com.darkphoton.data_visualiser_project.data.raw.RIndicator;

public class DieselPumpPrice extends NegativeIndicator {
    public final static String id = "EP.PMP.DESL.CD";
    public final static String name = "Diesel Pump Price";
    public final static String title = "Pump price for diesel fuel (US$ per liter)";

    public DieselPumpPrice(RIndicator indicator) {
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
