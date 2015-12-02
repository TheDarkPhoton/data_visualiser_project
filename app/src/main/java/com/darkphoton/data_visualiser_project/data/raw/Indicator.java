package com.darkphoton.data_visualiser_project.data.raw;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Indicator {
    private String _id;
    private String _name;
    private ArrayList<Data> _data = new ArrayList<>();

    public Indicator(String id, String name){
        _id = id;
        _name = name;
    }

    public void addData(Data data){
        _data.add(data);
    }

    public void addData(JSONObject data_unit) throws JSONException {
        Data data = new Data(data_unit.getString("date"), data_unit.getString("decimal"), data_unit.getString("value"));

        int index = _data.indexOf(data);

        if (index >= 0)
            _data.remove(index);

        _data.add(data);
    }

    public void updateData(ArrayList<Data> data){
        for (Data d : data) {
            int i = _data.indexOf(d);

            if (i >= 0)
                _data.remove(i);

            _data.add(d);
        }
    }

    public ArrayList<Data> getData(){
        return _data;
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
