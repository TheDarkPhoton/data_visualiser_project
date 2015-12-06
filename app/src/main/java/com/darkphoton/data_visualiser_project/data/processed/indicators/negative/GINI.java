package com.darkphoton.data_visualiser_project.data.processed.indicators.negative;

import com.darkphoton.data_visualiser_project.data.processed.indicators.NegativeIndicator;
import com.darkphoton.data_visualiser_project.data.raw.RIndicator;

public class GINI extends NegativeIndicator {
    public final static String id = "SI.POV.GINI";
    public final static String name = "GINI Index";
    public final static String title = "GINI index (World Bank estimate)";

    public GINI(RIndicator indicator) {
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
