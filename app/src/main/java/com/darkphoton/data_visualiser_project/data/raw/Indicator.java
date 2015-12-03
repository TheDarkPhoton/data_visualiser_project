package com.darkphoton.data_visualiser_project.data.raw;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Indicator {
    private String _id;
    private String _name;
    private HashMap<String, Data> _data = new HashMap<>();

    public Indicator(String id, String name){
        _id = id;
        _name = name;
    }

    public Indicator(JSONObject data_unit) throws JSONException {
        JSONObject jsonIndicator = data_unit.getJSONObject("indicator");

        _id = jsonIndicator.getString("id");
        _name = jsonIndicator.getString("value");

        Data data = new Data(data_unit);
        _data.put(data.getDate(), data);
    }

    public void addData(Data data){
        _data.put(data.getDate(), data);
    }

    public void updateData(JSONObject data_unit) throws JSONException {

    }

    public void updateData(HashMap<String, Data> data){
        for (Data new_data : data.values()) {
            Data old_data = _data.get(new_data.getDate());

            if (old_data == null)
                _data.put(new_data.getDate(), new_data);
            else
                old_data.updateData(new_data);
        }
    }

    public HashMap<String, Data> getData(){
        return _data;
    }

    public String getId(){
        return _id;
    }

    public String getName(){
        return _name;
    }

    @Override
    public String toString() {
        return _id;
    }

    @Override
    public boolean equals(Object o) {
        return _id.equals(o.toString());
    }
}
