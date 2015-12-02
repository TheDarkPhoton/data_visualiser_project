package com.darkphoton.data_visualiser_project.data.raw;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Country {
    private String _id;
    private String _name;
    private ArrayList<Indicator> _indicators = new ArrayList<>();

    public Country(String id, String name) {
        _id = id;
        _name = name;
    }

    public void addIndicator(Indicator indicator) {
        _indicators.add(indicator);
    }

    public void addIndicator(JSONObject data_unit) throws JSONException {
        JSONObject jsonIndicator = data_unit.getJSONObject("indicator");
        Indicator indicator = new Indicator(jsonIndicator.getString("id"), jsonIndicator.getString("value"));

        int index = _indicators.indexOf(indicator);

        if (index == -1){
            _indicators.add(indicator);

            Data data = new Data(data_unit.getString("date"), data_unit.getString("decimal"), data_unit.getString("value"));
            indicator.addData(data);
        } else {
            _indicators.get(index).addData(data_unit);
        }
    }

    public void updateIndicators(ArrayList<Indicator> indicators) {
        for (Indicator ind: indicators) {
            int i = _indicators.indexOf(ind);

            if (i == -1)
                _indicators.add(ind);
            else
                _indicators.get(i).updateData(ind.getData());
        }
    }

    public ArrayList<Indicator> getIndicators(){
        return _indicators;
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
