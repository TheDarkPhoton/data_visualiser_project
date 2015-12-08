package com.darkphoton.data_visualiser_project.data.processed;

import java.util.HashMap;
import java.util.Map;

public class PIndicatorGroup {
    private String _name;
    private Map<String, Class> _indicators;

    public PIndicatorGroup (String name, HashMap<String, Class> indicators){
        _name = name;
        _indicators = indicators;
    }

    public String getName(){
        return _name;
    }

    public Map<String, Class> getIndicators(){
        return _indicators;
    }
}
