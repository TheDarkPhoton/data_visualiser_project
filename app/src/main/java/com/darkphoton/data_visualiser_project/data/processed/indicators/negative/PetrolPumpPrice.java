package com.darkphoton.data_visualiser_project.data.processed.indicators.negative;

import com.darkphoton.data_visualiser_project.data.processed.indicators.NegativeIndicator;
import com.darkphoton.data_visualiser_project.data.raw.RIndicator;

import java.io.Serializable;

public class PetrolPumpPrice extends NegativeIndicator {
    public final static String id = "EP.PMP.SGAS.CD";
    public final static String name = "Petrol Pump Price";
    public final static String title = "Pump price for petrol fuel (US$ per liter)";
    public final static String description = "Fuel prices refer to the pump prices of the most widely sold grade of gasoline. Prices have been converted from the local currency to U.S. dollars.";

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

    public String getDescription() {
        return description;
    }
}
