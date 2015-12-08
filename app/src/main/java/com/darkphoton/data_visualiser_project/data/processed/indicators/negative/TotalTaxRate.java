package com.darkphoton.data_visualiser_project.data.processed.indicators.negative;

import com.darkphoton.data_visualiser_project.data.processed.indicators.NegativeIndicator;
import com.darkphoton.data_visualiser_project.data.raw.RIndicator;

public class TotalTaxRate extends NegativeIndicator {
    public final static String id = "IC.TAX.TOTL.CP.ZS";
    public final static String name = "Total Tax Rate";
    public final static String title = "Total tax rate (% of commercial profits)";
    public final static String description = "Total tax rate measures the amount of taxes and mandatory contributions payable by businesses after accounting for allowable deductions and exemptions as a share of commercial profits. Taxes withheld (such as personal income tax) or collected and remitted to tax authorities (such as value added taxes, sales taxes or goods and service taxes) are excluded.";

    public TotalTaxRate(RIndicator indicator) {
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
