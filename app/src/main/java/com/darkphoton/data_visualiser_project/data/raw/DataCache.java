package com.darkphoton.data_visualiser_project.data.raw;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class DataCache {
    private HashMap<String, Country> _countries = new HashMap<>();

    public DataCache(){}

    public void updateDataCache(JSONObject data_unit) throws JSONException {
        Country new_country = new Country(data_unit);

        Country old_country = _countries.get(new_country.getId());
        if (old_country == null)
            _countries.put(new_country.getId(), new_country);
        else
            old_country.updateIndicators(data_unit);
    }

    public void updateDataCache(HashMap<String, Country> countries){
        for (Country new_country : countries.values()) {
            Country old_country = _countries.get(new_country.getId());
            if (old_country == null)
                _countries.put(new_country.getId(), new_country);
            else
                old_country.updateIndicators(new_country.getIndicators());
        }
    }

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
