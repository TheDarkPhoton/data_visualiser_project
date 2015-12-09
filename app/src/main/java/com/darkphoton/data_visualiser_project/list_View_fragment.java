package com.darkphoton.data_visualiser_project;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.darkphoton.data_visualiser_project.data.processed.PCountry;
import com.darkphoton.data_visualiser_project.data.processed.PIndicator;

import java.util.List;

public class List_View_fragment extends Fragment {
    private PCountry countrySelected;
    private TextView country;

    public List_View_fragment(PCountry countrySelected){

        this.countrySelected = countrySelected;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view2 = inflater.inflate(R.layout.fragment_list__view , container , false);

        country = (TextView) view2.findViewById(R.id.country);
        country.setText(countrySelected.getName());
        for (PIndicator pIndicator : countrySelected.getIndicators().values()) {
            pIndicator.getAverage();
        }
        Log.d("soo" ,"hello world");
        return view2;
    }

//    public void getContrySeclected( final PCountry countrySelected) {
//        countrySelected.getIndicators();
//    }

}
