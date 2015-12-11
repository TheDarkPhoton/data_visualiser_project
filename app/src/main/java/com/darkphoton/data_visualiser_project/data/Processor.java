package com.darkphoton.data_visualiser_project.data;

import android.content.Context;

import com.darkphoton.data_visualiser_project.data.processed.PCountry;
import com.darkphoton.data_visualiser_project.data.processed.PIndicator;
import com.darkphoton.data_visualiser_project.data.raw.RCountry;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Processor implements Serializable {
    private ArrayList<PCountry> _countries = new ArrayList<PCountry>();

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

        _countries = new ArrayList<>(_countries.subList(0, (count > 1) ? count : 1));
    }

    public void normalize(){
        Collection<PIndicator> indicators = _countries.get(0).getIndicators().values();

        for (PIndicator indicator : indicators) {
            String indicator_key = indicator.getId();

            double highest = _countries.get(0).getIndicator(indicator_key).getAverage();
            double lowest = _countries.get(0).getIndicator(indicator_key).getAverage();
            for (int i = 1; i < _countries.size(); i++) {
                PIndicator e = _countries.get(i).getIndicator(indicator_key);

                double average = e.getAverage();

                if (highest < average || (Double.isNaN(highest) && !Double.isNaN(average)))
                    highest = average;

                if (lowest > average || (Double.isNaN(lowest) && !Double.isNaN(average)))
                    lowest = average;
            }

            if (highest == lowest)
                continue;

            for (PCountry country : _countries) {
                PIndicator e = country.getIndicator(indicator_key);
                e.normalize(highest, lowest);
            }
        }
    }

    public List<PCountry> getCountries(){
        return _countries;
    }

    public static Processor deserialize(Context context){
        Processor processor = null;
        try {
            FileInputStream fis = context.openFileInput("processed_data.dat");
            ObjectInputStream is = new ObjectInputStream(fis);
            processor = (Processor) is.readObject();
            is.close();
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (OptionalDataException e) {
            e.printStackTrace();
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return processor;
    }

    public static void serialize(Context context, Processor processor){
        try {
            FileOutputStream fos = context.openFileOutput("processed_data.dat", Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(processor);
            os.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        String output = "";

        for (PCountry country : _countries) {
//            output += country.getId() + "\n";
            output += country.getName() + "\n";
            output += "    value: " + country.getValue() + "\n";
            for (PIndicator indicator : country.getIndicators().values()) {
                output += "    " + indicator.getTitle() + ":\n";
                output += "        average: " + nf.format(indicator.getAverage()) + "\n";
                output += "        normalized: " + indicator.getNormalizedAverage() + "\n";
            }
            output += "\n";
        }

        return output;
    }
}
