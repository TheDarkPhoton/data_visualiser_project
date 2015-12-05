package com.darkphoton.data_visualiser_project.data.processed;

import com.darkphoton.data_visualiser_project.data.processed.indicators.ElectricityAccess;
import com.darkphoton.data_visualiser_project.data.processed.indicators.ElectricityAccessRural;
import com.darkphoton.data_visualiser_project.data.processed.indicators.ElectricityAccessUrban;
import com.darkphoton.data_visualiser_project.data.processed.indicators.GDP;
import com.darkphoton.data_visualiser_project.data.processed.indicators.NonSolidFuelAccess;
import com.darkphoton.data_visualiser_project.data.processed.indicators.SanitationFacilities;
import com.darkphoton.data_visualiser_project.data.processed.indicators.SanitationFacilitiesRural;
import com.darkphoton.data_visualiser_project.data.processed.indicators.SanitationFacilitiesUrban;
import com.darkphoton.data_visualiser_project.data.processed.indicators.WaterSources;
import com.darkphoton.data_visualiser_project.data.processed.indicators.WaterSourcesRural;
import com.darkphoton.data_visualiser_project.data.processed.indicators.WaterSourcesUrban;
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

        if (count > 0)
            _average = total / count;
    }

    public void normalize(double highest, double lowest){
        _normalized = (_average - lowest) / (highest - lowest);
    }

    public double getAverage(){
        return _average;
    }

    public double getNormalizedAverage(){
        return _normalized;
    }

    public abstract String getId();
    public abstract String getName();
    public abstract String getTitle();
}
