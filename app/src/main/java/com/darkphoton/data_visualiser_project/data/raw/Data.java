package com.darkphoton.data_visualiser_project.data.raw;

import org.json.JSONException;
import org.json.JSONObject;

public class Data {
    private String _date;
    private String _decimal;
    private double _value;

    public Data(String date, String decimal, String value){
        _date = date;
        _decimal = decimal;

        try {
            _value = Double.parseDouble(value);
        } catch (NumberFormatException e) {
            _value = 0;
        }
    }

    public Data(JSONObject data_unit) throws JSONException {
        _date = data_unit.getString("date");
        _decimal = data_unit.getString("decimal");

        try {
            _value = Double.parseDouble(data_unit.getString("value"));
        } catch (NumberFormatException e) {
            _value = 0;
        }
    }

    public void updateData(Data data){
        _decimal = data.getDecimal();
        _value = data.getValue();
    }

    public String getDate(){
        return _date;
    }

    public String getDecimal(){
        return _decimal;
    }

    public double getValue(){
        return _value;
    }

    @Override
    public String toString() {
        return _date;
    }

    @Override
    public boolean equals(Object o) {
        return _date.equals(o.toString());
    }
}
