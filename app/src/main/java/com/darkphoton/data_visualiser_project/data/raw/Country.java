package com.darkphoton.data_visualiser_project.data.raw;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Country {
    private String _id;
    private String _name;
    private HashMap<String, Indicator> _indicators = new HashMap<>();

    public Country(String id, String name) {
        _id = id;
        _name = name;
    }

    public Country(JSONObject data_unit) throws JSONException {
        JSONObject jsonCountry = data_unit.getJSONObject("country");

        _id = jsonCountry.getString("id");
        _name = jsonCountry.getString("value");

        Indicator indicator = new Indicator(data_unit);
        _indicators.put(indicator.getId(), indicator);
    }

    public void addIndicator(Indicator indicator) {
        _indicators.put(indicator.getId(), indicator);
    }


    public void updateIndicators(JSONObject data_unit) throws JSONException {
        Indicator new_indicator = new Indicator(data_unit);

        Indicator old_indicator = _indicators.get(new_indicator.getId());
        if (old_indicator == null)
            _indicators.put(new_indicator.getId(), new_indicator);
        else
            old_indicator.updateData(data_unit);
    }

    public void updateIndicators(HashMap<String, Indicator> indicators) {
        for (Indicator new_indicator : indicators.values()) {
            Indicator old_indicator = _indicators.get(new_indicator.getId());

            if (old_indicator == null)
                _indicators.put(new_indicator.getId(), new_indicator);
            else
                old_indicator.updateData(new_indicator.getData());
        }
    }

    public HashMap<String, Indicator> getIndicators(){
        return _indicators;
    }

    public String getId(){
        return _id;
    }

    public String getName(){
        return _name;
    }

    @Override
    public String toString() {
        return "{id[" + _id + "], name[" + _name + "]}";
    }

    @Override
    public boolean equals(Object o) {
        Country c = (Country) o;
        return _id.equals(c.getId()) && _name.equals(c.getName()) && _indicators.equals(c.getIndicators());
    }
}
