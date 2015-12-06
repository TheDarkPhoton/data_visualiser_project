package com.darkphoton.data_visualiser_project.data.processed.indicators.positive;

import com.darkphoton.data_visualiser_project.data.processed.indicators.PositiveIndicator;
import com.darkphoton.data_visualiser_project.data.raw.RIndicator;

public class DepositInterestRate extends PositiveIndicator {
    public final static String id = "FR.INR.DPST";
    public final static String name = "Deposit Interest Rate";
    public final static String title = "Deposit interest rate (%)";

    public DepositInterestRate(RIndicator indicator){
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
