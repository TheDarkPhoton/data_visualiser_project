package com.darkphoton.data_visualiser_project.data.processed;

import com.darkphoton.data_visualiser_project.data.raw.DataCache;
import com.darkphoton.data_visualiser_project.data.raw.RCountry;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;

public class Processor {
    private ArrayList<PCountry> _countries = new ArrayList<>();

    private final static DecimalFormat nf = new DecimalFormat("#.##");

    public Processor(DataCache cache){
        for (RCountry c : cache.getCountries().values()) {
            _countries.add(new PCountry(c));
        }
    }

    public void normalize(){
        Collection<PIndicator> indicators = _countries.get(0).getIndicators().values();

        for (PIndicator indicator : indicators) {
            String indicator_key = indicator.getId();

            double highest = _countries.get(0).getIndicator(indicator_key).getAverage();
            for (int i = 1; i < _countries.size(); i++) {
                double average = _countries.get(i).getIndicator(indicator_key).getAverage();
                if (highest < average)
                    highest = average;
            }

            if (highest == 0)
                continue;

            for (PCountry country : _countries) {
                country.getIndicator(indicator_key).normalize(highest);
            }
        }
    }

    public ArrayList<PCountry> getCountries(){
        return _countries;
    }

    @Override
    public String toString() {
        String output = "";

        for (PCountry country : _countries) {
            output += country.getName() + "\n";
            for (PIndicator indicator : country.getIndicators().values()) {
                if (indicator.getNormalizedAverage() < 0.5)
                    continue;

                output += "    " + indicator.getName() + ":\n";
                output += "        average: " + nf.format(indicator.getAverage()) + "\n";
                output += "        normalized: " + nf.format(indicator.getNormalizedAverage()) + "\n";
            }
        }

        return output;
    }
}
