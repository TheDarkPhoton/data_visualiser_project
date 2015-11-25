package com.darkphoton.data_visualiser_project;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by darkphoton on 25/11/15.
 */
public interface Job {
    void run(JSONObject json) throws JSONException;
}
