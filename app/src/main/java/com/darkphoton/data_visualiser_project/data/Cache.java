package com.darkphoton.data_visualiser_project.data;

import com.darkphoton.data_visualiser_project.data.raw.RCountry;
import com.darkphoton.data_visualiser_project.data.raw.RData;
import com.darkphoton.data_visualiser_project.data.raw.RIndicator;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Stores the raw data from the world bank.
 */
public class Cache {
    private HashMap<String, RCountry> _countries = new HashMap<>();                  // Stores indicator data for every country
    public final static String[] ignored = {                                         // The list of ignored areas (collections of countries)
            "1W", "XD", "OE", "XS", "Z7", "XO",
            "XP", "Z4", "XU", "EU", "XT", "XC",
            "4E", "ZJ", "XR", "XN", "XJ", "ZQ",
            "1A", "8S", "7E", "ZG", "ZF", "XQ",
            "B8", "XL", "F1", "XE", "XM", "S1",
            "S4", "S3", "LA", "S2"
    };

    /**
     * Updates the data in the cache using the provided JSON object.
     * @param data_unit World bank JSON object that contain information about country, indicator and data
     * @throws JSONException
     */
    public void updateDataCache(JSONObject data_unit) throws JSONException {
        RCountry new_country = new RCountry(data_unit);
        if (Arrays.asList(ignored).contains(new_country.getId()))
            return;

        RCountry old_country = _countries.get(new_country.getId());
        if (old_country == null)
            _countries.put(new_country.getId(), new_country);
        else
            old_country.updateIndicators(data_unit);
    }

    /**
     * Updates the data cache using the provided countries hash.
     * @param countries is a hash map with every country to be added or updated with new data.
     */
    public void updateDataCache(HashMap<String, RCountry> countries){
        for (RCountry new_country : countries.values()) {
            RCountry old_country = _countries.get(new_country.getId());
            if (old_country == null)
                _countries.put(new_country.getId(), new_country);
            else
                old_country.updateIndicators(new_country.getIndicators());
        }
    }

    /**
     * Gets the list of countries.
     * @return Hash map of all countries.
     */
    public HashMap<String, RCountry> getCountries(){
        return _countries;
    }

    @Override
    public String toString() {
        String output = "";

        for (RCountry c : _countries.values()) {
            output += "id: " + c.getId() + "\n";
            output += "name: " + c.getName() + "\n";

            for (RIndicator i : c.getIndicators().values()) {
                output += "    id: " + i.getId() + "\n";
                output += "    name: " + i.getName() + "\n";

                for (RData d : i.getData().values()) {
                    output += "        date: " + d.getDate() + "\n";
                    output += "        decimal: " + d.getDecimal() + "\n";
                    output += "        value: " + d.getValue() + "\n";
                }
                output += "\n";
            }
            output += "\n";
        }
        output += "\n";

        return output;
    }
}
