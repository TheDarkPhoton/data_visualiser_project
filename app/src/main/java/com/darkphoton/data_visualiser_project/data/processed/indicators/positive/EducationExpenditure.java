package com.darkphoton.data_visualiser_project.data.processed.indicators.positive;

import com.darkphoton.data_visualiser_project.data.processed.indicators.PositiveIndicator;
import com.darkphoton.data_visualiser_project.data.raw.RIndicator;

public class EducationExpenditure extends PositiveIndicator {
    public final static String id = "SE.XPD.TOTL.GD.ZS";
    public final static String name = "Education Expenditure";
    public final static String title = "Government expenditure on education, total (% of GDP)";

    public EducationExpenditure(RIndicator indicator){
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
