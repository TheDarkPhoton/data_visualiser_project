package com.darkphoton.data_visualiser_project.data.processed.indicators.positive;

import com.darkphoton.data_visualiser_project.data.processed.indicators.PositiveIndicator;
import com.darkphoton.data_visualiser_project.data.raw.RIndicator;

public class DepositInterestRate extends PositiveIndicator {
    public final static String id = "FR.INR.DPST";
    public final static String name = "Deposit Interest Rate";
    public final static String title = "Deposit interest rate (%)";
    public final static String description = "Deposit interest rate is the rate paid by commercial or similar banks for demand, time, or savings deposits. The terms and conditions attached to these rates differ by country, however, limiting their comparability.";

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

    public String getDescription() {
        return description;
    }
}
