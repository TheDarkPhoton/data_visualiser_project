package com.darkphoton.data_visualiser_project.data.processed.indicators;

import com.darkphoton.data_visualiser_project.data.processed.PIndicator;
import com.darkphoton.data_visualiser_project.data.raw.RIndicator;

public class NonSolidFuelAccess extends PIndicator {
    public final static String id = "EG.NSF.ACCS.ZS";
    public final static String name = "Access to Non-Solid Fuels";
    public final static String title = "Access to Non-Solid Fuels (% of population)";

    public NonSolidFuelAccess(RIndicator indicator){
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
