package com.darkphoton.data_visualiser_project.data.raw;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DataCache {
    private ArrayList<Country> _countries = new ArrayList<>();

    public DataCache(){}

    public DataCache(JSONArray indicators) throws JSONException {
        for (int i = 0; i < indicators.length(); ++i) {
            JSONObject data_unit = indicators.getJSONObject(i);

            JSONObject jsonCountry = data_unit.getJSONObject("country");
            Country country = new Country(jsonCountry.getString("id"), jsonCountry.getString("value"));
            int index = _countries.indexOf(country);

            if (index == -1) {
                _countries.add(country);

                JSONObject jsonIndicator = data_unit.getJSONObject("indicator");
                Indicator indicator = new Indicator(jsonIndicator.getString("id"), jsonIndicator.getString("value"));
                country.addIndicator(indicator);

                Data data = new Data(data_unit.getString("date"), data_unit.getString("decimal"), data_unit.getString("value"));
                indicator.addData(data);
            }
            else
                _countries.get(index).addIndicator(data_unit);
        }
    }

    public void updateDataCache(ArrayList<Country> countries){
        for (Country c : countries) {
            int i = _countries.indexOf(c);

            if (i == -1)
                _countries.add(c);
            else
                _countries.get(i).updateIndicators(c.getIndicators());
        }
    }

    public ArrayList<Country> getCountries(){
        return _countries;
    }
}
