package com.darkphoton.data_visualiser_project;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

/**
 * Created by Admin on 12/6/2015.
 */
public class CustomAdapter extends ArrayAdapter {
    Model[] modelItems = null;
    Context context;

    public CustomAdapter(Context context, Model[] resource){
        super(context, R.layout.row, resource);
        this.context=context;
        this.modelItems = resource;
    }

    public View getView(final int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.row, parent, false);
        CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.checkBox1);
        convertView.setOnClickListener(new menuOnClickListener(checkBox));
        TextView name = (TextView) convertView.findViewById(R.id.textView1);
        name.setText(modelItems[position].getName());
        if (modelItems[position].isEnabled()) {
            checkBox.setChecked(true);
        } else {
            checkBox.setChecked(false);
        }
        return convertView;
    }
}
