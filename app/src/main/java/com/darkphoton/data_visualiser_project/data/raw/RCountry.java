package com.darkphoton.data_visualiser_project.data.raw;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Stores world bank data of the country, this includes indicates and those include actual data.
 */
public class RCountry implements Serializable {
    private String _id;                                                         //The id given by the world bank
    private String _name;                                                       //The name given by the world bank
    private HashMap<String, RIndicator> _indicators = new HashMap<>();           //The list of indicators stored for this country

    public RCountry(RCountry country){
        _id = country.getId();
        _name = country.getName();
    }

    /**
     * Defines country with default parameters.
     * @param id is a unique identifier given by the world bank.
     * @param name is the name of the country given by the world bank.
     */
    public RCountry(String id, String name, HashMap<String, RIndicator> indicators) {
        _id = id;
        _name = name;
        _indicators = indicators;
    }

    /**
     * Defines country with parameters from json object.
     * @param data_unit is a json object with keys on country, indicators and data.
     * @throws JSONException
     */
    public RCountry(JSONObject data_unit) throws JSONException {
        JSONObject jsonCountry = data_unit.getJSONObject("country");

        _id = jsonCountry.getString("id");
        _name = jsonCountry.getString("value");

        RIndicator indicator = new RIndicator(data_unit);

        _indicators.put(indicator.getId(), indicator);
    }

    public void addIndicator(RIndicator indicator){
        _indicators.put(indicator.getId(), indicator);
    }

    /**
     * Updates indicator with parameters from json object.
     * @param data_unit is a json object with keys on indicators and data.
     * @throws JSONException
     */
    public void updateIndicators(JSONObject data_unit) throws JSONException {
        RIndicator new_indicator = new RIndicator(data_unit);

        RIndicator old_indicator = _indicators.get(new_indicator.getId());
        if (old_indicator == null) {
            _indicators.put(new_indicator.getId(), new_indicator);
        } else {
            old_indicator.updateDataSet(data_unit);
        }
    }

    /**
     * Updates indicator with the list of new indicators.
     * @param indicators is a hash map of indicators to be added or updated.
     */
    public void updateIndicators(HashMap<String, RIndicator> indicators) {
        for (RIndicator new_indicator : indicators.values()) {
            RIndicator old_indicator = _indicators.get(new_indicator.getId());

            if (old_indicator == null){
                _indicators.put(new_indicator.getId(), new_indicator);
            } else {
                old_indicator.updateDataSet(new_indicator.getData());
            }
        }
    }

    /**
     * Gets the list of indicators.
     * @return Hash map of all indicators that belongs to this object.
     */
    public HashMap<String, RIndicator> getIndicators(){
        return _indicators;
    }

    /**
     * Determines if a country has any indicators.
     * @return true if a country has at least one indicator.
     */
    public boolean isEmpty(){
        return _indicators.isEmpty();
    }

    /**
     * Gets the id of the object.
     * @return id as defined by the world bank.
     */
    public String getId(){
        return _id;
    }

    /**
     * Gets the name of the object.
     * @return name as defined by the world bank.
     */
    public String getName(){
        return _name;
    }

    @Override
    public String toString() {
        return "id[" + _id + "], name[" + _name + "]";
    }

    @Override
    public boolean equals(Object o) {
        RCountry c = (RCountry) o;
        return _id.equals(c.getId()) && _name.equals(c.getName()) && _indicators.equals(c.getIndicators());
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();

        JSONArray data = new JSONArray();
        for (RIndicator i : _indicators.values()) {
            data.put(i.toJSON());
        }

        json.put("id", _id);
        json.put("name", _name);
        json.put("indicators", data);

        return json;
    }

    public static RCountry fromJSON(JSONObject obj) throws JSONException {
        HashMap<String, RIndicator> indicators = new HashMap<>();

        JSONArray jsonData = obj.getJSONArray("indicators");
        for (int i = 0; i < jsonData.length(); i++) {
            RIndicator indicator = RIndicator.fromJSON(jsonData.getJSONObject(i));
            indicators.put(indicator.getId(), indicator);
        }

        return new RCountry(obj.getString("id"), obj.getString("name"), indicators);
    }
}
