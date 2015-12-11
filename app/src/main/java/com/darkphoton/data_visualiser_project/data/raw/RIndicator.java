package com.darkphoton.data_visualiser_project.data.raw;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Stores data on a particular world bank topic.
 */
public class RIndicator implements Serializable {
    private String _id;                                             //indicator id as defined by world data
    private String _name;                                           //indicator name as defined by world data
    private HashMap<String, RData> _data = new HashMap<>();          //The list of data points in this indicator

    /**
     * Defines default indicator.
     * @param id The id of the indicator as defined by the world bank.
     * @param name The name of the indicator as defined by the world bank.
     */
    public RIndicator(String id, String name, HashMap<String, RData> data){
        _id = id;
        _name = name;
        _data = data;
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
     * Updates or adds the raw data unit provided.
     * @param new_data The data to be added or updated.
     */
    private void updateData(RData new_data){
        RData old_data = _data.get(new_data.getDate());
        if (old_data == null){
            _data.put(new_data.getDate(), new_data);
        } else {
            old_data.updateData(new_data);
        }
    }

    /**
     * Updates data with parameters from json object.
     * @param data_unit is a json object with data.
     * @throws JSONException
     */
    public void updateDataSet(JSONObject data_unit) throws JSONException {
        updateData(new RData(data_unit));
    }

    /**
     * Updates data with the list of new data.
     * @param data is a hash map of data to be added or updated.
     */
    public void updateDataSet(HashMap<String, RData> data){
        for (RData new_data : data.values()) {
            updateData(new_data);
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
     * Determines whether indicator has any data units.
     * @return true if there is at least one data unit on this indicator.
     */
    public boolean isEmpty(){
        return _data.isEmpty();
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

    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();

        JSONArray data = new JSONArray();
        for (RData d : _data.values()) {
            data.put(d.toJSON());
        }

        json.put("id", _id);
        json.put("name", _name);
        json.put("data", data);

        return json;
    }

    public static RIndicator fromJSON(JSONObject obj) throws JSONException {
        HashMap<String, RData> data = new HashMap<>();

        JSONArray jsonData = obj.getJSONArray("data");
        for (int i = 0; i < jsonData.length(); i++) {
            JSONObject tmp = jsonData.getJSONObject(i);
            RData d = RData.fromJSON(tmp);
            data.put(d.getDate(), d);
        }

        return new RIndicator(obj.getString("id"), obj.getString("name"), data);
    }
}
