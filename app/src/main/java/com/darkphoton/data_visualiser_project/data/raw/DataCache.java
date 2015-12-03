package com.darkphoton.data_visualiser_project.data.raw;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Stores the raw data from the world bank.
 */
public class DataCache {
    private HashMap<String, Country> _countries = new HashMap<>();                  //Stores indicator data for every country

    /**
     * Updates the data in the cache using the provided JSON object.
     * @param data_unit World bank JSON object that contain information about country, indicator and data
     * @throws JSONException
     */
    public void updateDataCache(JSONObject data_unit) throws JSONException {
        Country new_country = new Country(data_unit);

        Country old_country = _countries.get(new_country.getId());
        if (old_country == null)
            _countries.put(new_country.getId(), new_country);
        else
            old_country.updateIndicators(data_unit);
    }

    /**
     * Updates the data cache using the provided countries hash.
     * @param countries is a hash map with every country to be added or updated with new data.
     */
    public void updateDataCache(HashMap<String, Country> countries){
        for (Country new_country : countries.values()) {
            Country old_country = _countries.get(new_country.getId());
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
    public HashMap<String, Country> getCountries(){
        return _countries;
    }

    @Override
    public String toString() {
        String output = "";

        for (Country c : _countries.values()) {
            output += "id: " + c.getId() + "\n";
            output += "name: " + c.getName() + "\n";

            for (Indicator i : c.getIndicators().values()) {
                output += "    id: " + i.getId() + "\n";
                output += "    name: " + i.getName() + "\n";

                for (Data d : i.getData().values()) {
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
