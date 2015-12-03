package com.darkphoton.data_visualiser_project.data.raw;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class DataCache {
    private HashMap<String, Country> _countries = new HashMap<>();

    public DataCache(){}

//    public DataCache(JSONArray indicators) throws JSONException {
//        for (int i = 0; i < indicators.length(); ++i) {
//            JSONObject data_unit = indicators.getJSONObject(i);
//
//            JSONObject jsonCountry = data_unit.getJSONObject("country");
//            Country country = new Country(jsonCountry.getString("id"), jsonCountry.getString("value"));
//            int index = _countries.indexOf(country);
//
//            if (index == -1) {
//                _countries.add(country);
//
//                JSONObject jsonIndicator = data_unit.getJSONObject("indicator");
//                Indicator indicator = new Indicator(jsonIndicator.getString("id"), jsonIndicator.getString("value"));
//                country.addIndicator(indicator);
//
//                Data data = new Data(data_unit.getString("date"), data_unit.getString("decimal"), data_unit.getString("value"));
//                indicator.addData(data);
//            }
//            else
//                _countries.get(index).addIndicator(data_unit);
//        }
//    }

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
}
