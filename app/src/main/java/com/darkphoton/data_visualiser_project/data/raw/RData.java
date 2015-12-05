package com.darkphoton.data_visualiser_project.data.raw;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Stores information on a single data point.
 */
public class RData {
    private String _date;                                           //Date of the data point
    private String _decimal;                                        //
    private double _value;                                          //Value of the data point

    /**
     * Defines default data object.
     * @param date Date of the data point.
     * @param decimal
     * @param value Value of the data point.
     */
    public RData(String date, String decimal, String value){
        _date = date;
        _decimal = decimal;

        try {
            _value = Double.parseDouble(value);
        } catch (NumberFormatException e) {
            _value = 0;
        }
    }

    /**
     * Defines a data point from the json object.
     * @param data_unit Json object with information of the data point, date, decimal and value.
     * @throws JSONException
     */
    public RData(JSONObject data_unit) throws JSONException {
        _date = data_unit.getString("date");
        _decimal = data_unit.getString("decimal");

        try {
            _value = Double.parseDouble(data_unit.getString("value"));
        } catch (NumberFormatException e) {
            _value = 0;
        }
    }

    /**
     * Updates the value of the data.
     * @param data An object with values to be used.
     */
    public void updateData(RData data){
        _decimal = data.getDecimal();
        _value = data.getValue();
    }

    /**
     * Gets the Date of the data point.
     * @return date of this data point.
     */
    public String getDate(){
        return _date;
    }

    /**
     * Gets the Decimal of the data point.
     * @return decimal of the data point.
     */
    public String getDecimal(){
        return _decimal;
    }

    /**
     * Gets the Value of the data point.
     * @return value of the data point.
     */
    public double getValue(){
        return _value;
    }

    @Override
    public String toString() {
        return "date[" + _date + "], decimal[" + _decimal + "], value[" + _value + "]";
    }

    @Override
    public boolean equals(Object o) {
        RData d = (RData) o;
        return _date.equals(d.getDate()) && _decimal.equals(d.getDecimal()) && _value == d.getValue();
    }
}