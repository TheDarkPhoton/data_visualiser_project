package com.darkphoton.data_visualiser_project.data.raw;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Stores data on a particular world bank topic.
 */
public class RIndicator {
    private String _id;                                             //indicator id as defined by world data
    private String _name;                                           //indicator name as defined by world data
    private HashMap<String, RData> _data = new HashMap<>();          //The list of data points in this indicator

    /**
     * Defines default indicator.
     * @param id The id of the indicator as defined by the world bank.
     * @param name The name of the indicator as defined by the world bank.
     */
    public RIndicator(String id, String name){
        _id = id;
        _name = name;
    }

    /**
     * Defines indicator using the provided json object.
     * @param data_unit Json object that contains information on indicator and data.
     * @throws JSONException
     */
    public RIndicator(JSONObject data_unit) throws JSONException {
        JSONObject jsonIndicator = data_unit.getJSONObject("indicator");

        _id = jsonIndicator.getString("id");
        _name = jsonIndicator.getString("value");

        RData data = new RData(data_unit);
        _data.put(data.getDate(), data);
    }

    /**
     * Updates data with parameters from json object.
     * @param data_unit is a json object with data.
     * @throws JSONException
     */
    public void updateData(JSONObject data_unit) throws JSONException {
        RData new_data = new RData(data_unit);

        RData old_data = _data.get(new_data.getDate());
        if (old_data == null)
            _data.put(new_data.getDate(), new_data);
        else
            old_data.updateData(new_data);
    }

    /**
     * Updates data with the list of new data.
     * @param data is a hash map of data to be added or updated.
     */
    public void updateData(HashMap<String, RData> data){
        for (RData new_data : data.values()) {
            RData old_data = _data.get(new_data.getDate());

            if (old_data == null)
                _data.put(new_data.getDate(), new_data);
            else
                old_data.updateData(new_data);
        }
    }

    /**
     * Gets the list of data.
     * @return Hash map of all data stored on this indicator.
     */
    public HashMap<String, RData> getData(){
        return _data;
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
        RIndicator i = (RIndicator) o;
        return _id.equals(i.getId()) && _name.equals(i.getName()) && _data.equals(i.getData());
    }
}
