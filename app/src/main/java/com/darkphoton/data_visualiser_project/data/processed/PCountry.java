package com.darkphoton.data_visualiser_project.data.processed;

import android.util.Log;

import com.darkphoton.data_visualiser_project.data.raw.RCountry;
import com.darkphoton.data_visualiser_project.data.raw.RIndicator;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

public class PCountry {
    private HashMap<String, PIndicator> _indicators = new HashMap<>();
    private String _id;
    private String _name;

    public PCountry(RCountry country) {
        _id = country.getId();
        _name = country.getName();

        for (RIndicator i : country.getIndicators().values()) {
            Class c = PIndicator.indicatorClasses.get(i.getId());
            try {
                _indicators.put(i.getId(), (PIndicator) c.getConstructor(RIndicator.class).newInstance(i));

            } catch (NoSuchMethodException e) {
                Log.i("PCountry", "Class not found while adding " + i.getName());
            } catch (InvocationTargetException e) {
                Log.i("PCountry", "Constructor not found while adding " + i.getName());
            } catch (InstantiationException e) {
                Log.i("PCountry", "Could not instantiate " + i.getName());
            } catch (IllegalAccessException e) {
                Log.i("PCountry", "Illegal access exception while adding " + i.getName());
            }
        }
    }

    public PIndicator getIndicator(String key){
        return _indicators.get(key);
    }

    public String getId() {
        return _id;
    }

    public String getName() {
        return _name;
    }

    public HashMap<String, PIndicator> getIndicators(){
        return _indicators;
    }
}