package com.darkphoton.data_visualiser_project.data.processed.indicators.positive;

import com.darkphoton.data_visualiser_project.data.processed.indicators.PositiveIndicator;
import com.darkphoton.data_visualiser_project.data.raw.RIndicator;

public class LiteracyRate extends PositiveIndicator {
    public final static String id = "SE.ADT.LITR.ZS";
    public final static String name = "Literacy Rate";
    public final static String title = "Literacy rate, adult total (% of people ages 15 and above)";

    public LiteracyRate(RIndicator indicator){
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
