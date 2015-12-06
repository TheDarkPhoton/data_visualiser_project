package com.darkphoton.data_visualiser_project.data.processed.indicators.positive;

import com.darkphoton.data_visualiser_project.data.processed.indicators.PositiveIndicator;
import com.darkphoton.data_visualiser_project.data.raw.RIndicator;

public class LegalRightsIndex extends PositiveIndicator {
    public final static String id = "IC.LGL.CRED.XQ";
    public final static String name = "Legal Rights Index";
    public final static String title = "Strength of legal rights index (0=weak to 12=strong)";

    public LegalRightsIndex(RIndicator indicator){
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
