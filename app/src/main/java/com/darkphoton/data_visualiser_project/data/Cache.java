package com.darkphoton.data_visualiser_project.data;

import android.content.Context;
import android.util.Log;

import com.darkphoton.data_visualiser_project.data.raw.RCountry;
import com.darkphoton.data_visualiser_project.data.raw.RData;
import com.darkphoton.data_visualiser_project.data.raw.RIndicator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

/**
 * Stores the raw data from the world bank.
 */
public class Cache implements Serializable {
    private HashMap<String, RCountry> _countries = new HashMap<>();                  // Stores indicator data for every country
    private Date _cache_age = null;

    public final static String[] ignored = {                                         // The list of ignored areas (collections of countries)
            "1W", "XD", "OE", "XS", "Z7", "XO",
            "XP", "Z4", "XU", "EU", "XT", "XC",
            "4E", "ZJ", "XR", "XN", "XJ", "ZQ",
            "1A", "8S", "7E", "ZG", "ZF", "XQ",
            "B8", "XL", "F1", "XE", "XM", "S1",
            "S4", "S3", "LA", "S2"
    };

    public Cache(){}

    public Cache(String json) {
        try {
            JSONObject jsonCache = new JSONObject(json);

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            _cache_age = format.parse((String) jsonCache.get("date"));

            JSONArray countries = jsonCache.getJSONArray("countries");
            for (int i = 0; i < countries.length(); i++) {
                RCountry country = RCountry.fromJSON(countries.getJSONObject(i));
                _countries.put(country.getId(), country);
            }
        } catch (JSONException | ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the data in the cache using the provided JSON object.
     * @param data_unit World bank JSON object that contain information about country, indicator and data
     * @throws JSONException
     */
    public void updateDataCache(JSONObject data_unit) throws JSONException {
        RCountry new_country = new RCountry(data_unit);
        if (Arrays.asList(ignored).contains(new_country.getId()))
            return;

        RCountry old_country = _countries.get(new_country.getId());
        if (old_country == null) {
            if (!new_country.isEmpty())
                _countries.put(new_country.getId(), new_country);
        } else {
            old_country.updateIndicators(data_unit);
        }
    }

    /**
     * Updates the data cache using the provided countries hash.
     * @param countries is a hash map with every country to be added or updated with new data.
     */
    public void updateDataCache(HashMap<String, RCountry> countries){
        for (RCountry new_country : countries.values()) {
            RCountry old_country = _countries.get(new_country.getId());
            if (old_country == null) {
                if (!new_country.isEmpty())
                    _countries.put(new_country.getId(), new_country);
            } else {
                old_country.updateIndicators(new_country.getIndicators());
            }
        }
    }

    public void addCountry(RCountry country){
        _countries.put(country.getId(), country);
    }

    /**
     * Gets the list of countries.
     * @return Hash map of all countries.
     */
    public HashMap<String, RCountry> getCountries(){
        return _countries;
    }

    @Override
    public String toString() {
        String output = "";

        for (RCountry c : _countries.values()) {
            output += "id: " + c.getId() + "\n";
            output += "name: " + c.getName() + "\n";

            for (RIndicator i : c.getIndicators().values()) {
                output += "    id: " + i.getId() + "\n";
                output += "    name: " + i.getName() + "\n";

                for (RData d : i.getData().values()) {
                    output += "        date: " + d.getDate() + "\n";
                    output += "        decimal: " + d.getDecimal() + "\n";
                    output += "        value: " + d.getValue() + "\n";
                }
                output += "\n";
            }
            output += "\n";
        }
        output += "\n";

        return output;
    }

    public static Cache deserialize(Context context){
        String json = "";
        try {
            InputStream is = context.openFileInput("raw_data.dat");

            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            json = new String(buffer, "UTF-8");

            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Cache(json);
    }

    public static void serialize(Context context, Cache cache){
        try {
            OutputStreamWriter osw = new OutputStreamWriter(context.openFileOutput("raw_data.dat", Context.MODE_PRIVATE));
            String s = cache.toJSON().toString();
            osw.write(s);
            osw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Cache subsetByIndicator(ArrayList<String> ids){
        Cache newCache = new Cache();

        for (RCountry country : _countries.values()) {
            RCountry newCountry = new RCountry(country);

            for (String id : ids) {
                RIndicator indicator = country.getIndicators().get(id);
                if (indicator != null)
                    newCountry.addIndicator(indicator);
            }

            newCache.addCountry(newCountry);
        }

        return newCache;
    }

    public void cachingCompleted(){
        _cache_age = new Date();
        Log.i("CACHE", "Caching completed " + _cache_age.toString());
    }

    public boolean IsOutdated(){
        Date current_date = new Date();
        long month = (30L * 604800000L);
        return _cache_age == null || _cache_age.getTime() < current_date.getTime() - month;
    }

    public JSONObject toJSON(){
        JSONObject json = new JSONObject();

        try {
            JSONArray countries = new JSONArray();
            for (RCountry c : _countries.values()) {
                countries.put(c.toJSON());
            }

            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            String date = f.format(_cache_age);

            json.put("date", date);
            json.put("countries", countries);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return json;
    }
}
