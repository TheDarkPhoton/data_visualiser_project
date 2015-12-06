package com.darkphoton.data_visualiser_project.data.processed.indicators.negative;

import com.darkphoton.data_visualiser_project.data.processed.indicators.NegativeIndicator;
import com.darkphoton.data_visualiser_project.data.raw.RIndicator;

public class PetrolPumpPrice extends NegativeIndicator {
    public final static String id = "EP.PMP.SGAS.CD";
    public final static String name = "Petrol Pump Price";
    public final static String title = "Pump price for petrol fuel (US$ per liter)";

    public PetrolPumpPrice(RIndicator indicator) {
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
