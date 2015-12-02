package com.darkphoton.data_visualiser_project.data.raw;

import org.json.JSONException;

public class Data {
    private String _date;
    private String _decimal;
    private double _value;

    public Data(String date, String decimal, String value){
        _date = date;
        _decimal = decimal;

        try {
            _value = Double.parseDouble(value);
        }
        catch (NumberFormatException e) {
            _value = 0;
        }
    }

    public String getDate(){
        return _date;
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
