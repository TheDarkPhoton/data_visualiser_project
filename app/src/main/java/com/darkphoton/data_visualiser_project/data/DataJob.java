package com.darkphoton.data_visualiser_project.data;

import org.json.JSONArray;
import org.json.JSONException;
import java.util.ArrayList;

public interface DataJob {
    void run(ArrayList<JSONArray> json) throws JSONException;
}
