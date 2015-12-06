package com.darkphoton.data_visualiser_project;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.darkphoton.data_visualiser_project.data.processed.PIndicator;

import java.util.List;

public class SideBarAdapter extends ArrayAdapter<PIndicator> {

    public SideBarAdapter(Context context, int resource, List<PIndicator> items) {
        super(context, resource, items);


    }
}
