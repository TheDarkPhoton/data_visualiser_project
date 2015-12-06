package com.darkphoton.data_visualiser_project.data;

import com.darkphoton.data_visualiser_project.data.processed.PCountry;
import com.darkphoton.data_visualiser_project.data.processed.PIndicator;
import com.darkphoton.data_visualiser_project.data.raw.RCountry;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Processor {
    private List<PCountry> _countries = new ArrayList<>();

    private final static DecimalFormat nf = new DecimalFormat("#.##");

    public Processor(Cache cache){
        for (RCountry c : cache.getCountries().values()) {
            _countries.add(new PCountry(c));
        }
    }

    public void reduceToTop(int count){
        for (PCountry country : _countries) {
            country.calculateValue();
        }

        Collections.sort(_countries, new Comparator<PCountry>() {
            @Override
            public int compare(PCountry lhs, PCountry rhs) {
                return ((Double) rhs.getValue()).compareTo(lhs.getValue());
            }
        });

        _countries = _countries.subList(0, (count > 1) ? count : 1);
    }

    public void normalize(){
        Collection<PIndicator> indicators = _countries.get(0).getIndicators().values();

        for (PIndicator indicator : indicators) {
            String indicator_key = indicator.getId();

            double highest = _countries.get(0).getIndicator(indicator_key).getAverage();
            double lowest = _countries.get(0).getIndicator(indicator_key).getAverage();
            for (int i = 1; i < _countries.size(); i++) {
                double average = _countries.get(i).getIndicator(indicator_key).getAverage();

                if (highest < average)
                    highest = average;

                if (lowest > average)
                    lowest = average;
            }

            if (highest == lowest)
                continue;

            for (PCountry country : _countries) {
                country.getIndicator(indicator_key).normalize(highest, lowest);
            }
        }
    }

    public List<PCountry> getCountries(){
        return _countries;
    }

    @Override
    public String toString() {
        String output = "";

        for (PCountry country : _countries) {
//            output += country.getId() + "\n";
            output += country.getName() + "\n";
            output += "    value: " + country.getValue() + "\n";
            for (PIndicator indicator : country.getIndicators().values()) {
                output += "    " + indicator.getName() + ":\n";
                output += "        average: " + nf.format(indicator.getAverage()) + "\n";
                output += "        normalized: " + indicator.getNormalizedAverage() + "\n";
            }
            output += "\n";
        }

        return output;
    }
}
