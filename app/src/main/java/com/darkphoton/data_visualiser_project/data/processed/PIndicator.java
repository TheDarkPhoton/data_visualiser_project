package com.darkphoton.data_visualiser_project.data.processed;

import com.darkphoton.data_visualiser_project.data.processed.indicators.negative.CausesOfDeath;
import com.darkphoton.data_visualiser_project.data.processed.indicators.negative.DieselPumpPrice;
import com.darkphoton.data_visualiser_project.data.processed.indicators.negative.GINI;
import com.darkphoton.data_visualiser_project.data.processed.indicators.negative.LongTermUnemploymentFemale;
import com.darkphoton.data_visualiser_project.data.processed.indicators.negative.LongTermUnemploymentMale;
import com.darkphoton.data_visualiser_project.data.processed.indicators.negative.PetrolPumpPrice;
import com.darkphoton.data_visualiser_project.data.processed.indicators.negative.PollutionMeanAnnualExposure;
import com.darkphoton.data_visualiser_project.data.processed.indicators.negative.PollutionWHOGuideline;
import com.darkphoton.data_visualiser_project.data.processed.indicators.negative.TotalTaxRate;
import com.darkphoton.data_visualiser_project.data.processed.indicators.positive.DepositInterestRate;
import com.darkphoton.data_visualiser_project.data.processed.indicators.positive.EducationExpenditure;
import com.darkphoton.data_visualiser_project.data.processed.indicators.positive.ElectricityAccess;
import com.darkphoton.data_visualiser_project.data.processed.indicators.positive.ElectricityAccessRural;
import com.darkphoton.data_visualiser_project.data.processed.indicators.positive.ElectricityAccessUrban;
import com.darkphoton.data_visualiser_project.data.processed.indicators.positive.GDP;
import com.darkphoton.data_visualiser_project.data.processed.indicators.positive.GDPPerCapita;
import com.darkphoton.data_visualiser_project.data.processed.indicators.positive.GrossSavings;
import com.darkphoton.data_visualiser_project.data.processed.indicators.positive.LegalRightsIndex;
import com.darkphoton.data_visualiser_project.data.processed.indicators.positive.LiteracyRate;
import com.darkphoton.data_visualiser_project.data.processed.indicators.positive.NonSolidFuelAccess;
import com.darkphoton.data_visualiser_project.data.processed.indicators.negative.PollutionDensity;
import com.darkphoton.data_visualiser_project.data.processed.indicators.positive.RailwayPassengersCarried;
import com.darkphoton.data_visualiser_project.data.processed.indicators.positive.RenewableInternalFreshwater;
import com.darkphoton.data_visualiser_project.data.processed.indicators.positive.SanitationFacilities;
import com.darkphoton.data_visualiser_project.data.processed.indicators.positive.SanitationFacilitiesRural;
import com.darkphoton.data_visualiser_project.data.processed.indicators.positive.SanitationFacilitiesUrban;
import com.darkphoton.data_visualiser_project.data.processed.indicators.positive.TotalReserves;
import com.darkphoton.data_visualiser_project.data.processed.indicators.positive.WaterSources;
import com.darkphoton.data_visualiser_project.data.processed.indicators.positive.WaterSourcesRural;
import com.darkphoton.data_visualiser_project.data.processed.indicators.positive.WaterSourcesUrban;
import com.darkphoton.data_visualiser_project.data.raw.RData;
import com.darkphoton.data_visualiser_project.data.raw.RIndicator;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class PIndicator {
    public final static Map<String, Class> indicatorClasses;

    static {
        HashMap<String, Class> map = new HashMap<>();
        map.put("NY.GDP.MKTP.CD", GDP.class);
        map.put("SH.STA.ACSN", SanitationFacilities.class);
        map.put("SH.STA.ACSN.RU", SanitationFacilitiesRural.class);
        map.put("SH.STA.ACSN.UR", SanitationFacilitiesUrban.class);
        map.put("SH.H2O.SAFE.ZS", WaterSources.class);
        map.put("SH.H2O.SAFE.RU.ZS", WaterSourcesRural.class);
        map.put("SH.H2O.SAFE.UR.ZS", WaterSourcesUrban.class);
        map.put("EG.NSF.ACCS.ZS", NonSolidFuelAccess.class);
        map.put("EG.ELC.ACCS.ZS", ElectricityAccess.class);
        map.put("EG.ELC.ACCS.RU.ZS", ElectricityAccessRural.class);
        map.put("EG.ELC.ACCS.UR.ZS", ElectricityAccessUrban.class);
        map.put("ER.H2O.INTR.PC", RenewableInternalFreshwater.class);
        map.put("EN.POP.DNST", PollutionDensity.class);
        map.put("SH.DTH.COMM.ZS", CausesOfDeath.class);
        map.put("SI.POV.GINI", GINI.class);
        map.put("NY.GNS.ICTR.CD", GrossSavings.class);
        map.put("FI.RES.TOTL.CD", TotalReserves.class);
        map.put("FR.INR.DPST", DepositInterestRate.class);
        map.put("IC.TAX.TOTL.CP.ZS", TotalTaxRate.class);
        map.put("IC.LGL.CRED.XQ", LegalRightsIndex.class);
        map.put("SL.UEM.LTRM.FE.ZS", LongTermUnemploymentFemale.class);
        map.put("SL.UEM.LTRM.MA.ZS", LongTermUnemploymentMale.class);
        map.put("SE.XPD.TOTL.GD.ZS", EducationExpenditure.class);
        map.put("SE.ADT.LITR.ZS", LiteracyRate.class);
        map.put("IS.RRS.PASG.KM", RailwayPassengersCarried.class);
        map.put("EP.PMP.DESL.CD", DieselPumpPrice.class);
        map.put("EP.PMP.SGAS.CD", PetrolPumpPrice.class);
        map.put("EN.ATM.PM25.MC.M3", PollutionMeanAnnualExposure.class);
        map.put("EN.ATM.PM25.MC.ZS", PollutionWHOGuideline.class);
        map.put("NY.GDP.PCAP.CD", GDPPerCapita.class);

        indicatorClasses = Collections.unmodifiableMap(map);
    }

    protected double _average = 0;
    protected double _normalized = 0;

    public PIndicator(RIndicator indicator){
        double total = 0;
        int count = 0;

        for (RData d : indicator.getData().values()) {
            if (d.getValue() != 0){
                total += d.getValue();
                ++count;
            }
        }

        _average = total / count;
    }

    public double getAverage(){
        return _average;
    }

    public double getNormalizedAverage(){
        return _normalized;
    }

    public abstract void normalize(double highest, double lowest);
    public abstract String getId();
    public abstract String getName();
    public abstract String getTitle();
}
