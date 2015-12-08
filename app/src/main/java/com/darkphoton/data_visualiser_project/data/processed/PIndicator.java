package com.darkphoton.data_visualiser_project.data.processed;

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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class PIndicator {
    public final static Map<String, Class> indicatorClasses;
    public final static List<PIndicatorGroup> indicatorGroups;

    static {
        List<PIndicatorGroup> indicators = new ArrayList<>();

        HashMap<String, Class> economics_map = new HashMap<>();
        economics_map.put("NY.GDP.MKTP.CD", GDP.class);
        economics_map.put("NY.GNS.ICTR.CD", GrossSavings.class);
        economics_map.put("FI.RES.TOTL.CD", TotalReserves.class);
        economics_map.put("IC.TAX.TOTL.CP.ZS", TotalTaxRate.class);
        economics_map.put("SL.UEM.LTRM.FE.ZS", LongTermUnemploymentFemale.class);
        economics_map.put("SL.UEM.LTRM.MA.ZS", LongTermUnemploymentMale.class);
        economics_map.put("FR.INR.DPST", DepositInterestRate.class);
        economics_map.put("NY.GDP.PCAP.CD", GDPPerCapita.class);
        indicators.add(new PIndicatorGroup("Economics", economics_map));

        HashMap<String, Class> group2_map = new HashMap<>();
        group2_map.put("SH.STA.ACSN", SanitationFacilities.class);
        group2_map.put("SH.STA.ACSN.RU", SanitationFacilitiesRural.class);
        group2_map.put("SH.STA.ACSN.UR", SanitationFacilitiesUrban.class);
        group2_map.put("SH.H2O.SAFE.ZS", WaterSources.class);
        group2_map.put("SH.H2O.SAFE.RU.ZS", WaterSourcesRural.class);
        group2_map.put("SH.H2O.SAFE.UR.ZS", WaterSourcesUrban.class);
        group2_map.put("ER.H2O.INTR.PC", RenewableInternalFreshwater.class);
        indicators.add(new PIndicatorGroup("Group 2", group2_map));

        HashMap<String, Class> energy_map = new HashMap<>();
        energy_map.put("EG.NSF.ACCS.ZS", NonSolidFuelAccess.class);
        energy_map.put("EG.ELC.ACCS.ZS", ElectricityAccess.class);
        energy_map.put("EG.ELC.ACCS.RU.ZS", ElectricityAccessRural.class);
        energy_map.put("EG.ELC.ACCS.UR.ZS", ElectricityAccessUrban.class);
        energy_map.put("EP.PMP.DESL.CD", DieselPumpPrice.class);
        energy_map.put("EP.PMP.SGAS.CD", PetrolPumpPrice.class);
        indicators.add(new PIndicatorGroup("Energy", energy_map));

        HashMap<String, Class> climate_map = new HashMap<>();
        climate_map.put("EN.POP.DNST", PollutionDensity.class);
        climate_map.put("EN.ATM.PM25.MC.M3", PollutionMeanAnnualExposure.class);
        climate_map.put("EN.ATM.PM25.MC.ZS", PollutionWHOGuideline.class);
        indicators.add(new PIndicatorGroup("Climate", climate_map));

        HashMap<String, Class> education_map = new HashMap<>();
        education_map.put("SE.XPD.TOTL.GD.ZS", EducationExpenditure.class);
        education_map.put("SE.ADT.LITR.ZS", LiteracyRate.class);
        indicators.add(new PIndicatorGroup("Education", education_map));

        HashMap<String, Class> other_map = new HashMap<>();
        other_map.put("SI.POV.GINI", GINI.class);
        other_map.put("IC.LGL.CRED.XQ", LegalRightsIndex.class);
        other_map.put("IS.RRS.PASG.KM", RailwayPassengersCarried.class);
        indicators.add(new PIndicatorGroup("Other", other_map));

        indicatorGroups = Collections.unmodifiableList(indicators);

        HashMap<String, Class> map = new HashMap<>();
        for (PIndicatorGroup indicator : indicators) {
            map.putAll(indicator.getIndicators());
        }

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
