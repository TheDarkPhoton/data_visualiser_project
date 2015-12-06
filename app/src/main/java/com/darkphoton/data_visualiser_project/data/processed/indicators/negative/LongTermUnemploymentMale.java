package com.darkphoton.data_visualiser_project.data.processed.indicators.negative;

import com.darkphoton.data_visualiser_project.data.processed.indicators.NegativeIndicator;
import com.darkphoton.data_visualiser_project.data.raw.RIndicator;

public class LongTermUnemploymentMale extends NegativeIndicator {
    public final static String id = "SL.UEM.LTRM.MA.ZS";
    public final static String name = "Long Term Unemployment - Male";
    public final static String title = "Long-term unemployment, male (% of male unemployment) ";

    public LongTermUnemploymentMale(RIndicator indicator) {
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
