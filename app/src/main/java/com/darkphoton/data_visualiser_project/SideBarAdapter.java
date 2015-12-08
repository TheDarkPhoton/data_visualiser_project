package com.darkphoton.data_visualiser_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;

import com.darkphoton.data_visualiser_project.data.processed.PIndicator;
import com.darkphoton.data_visualiser_project.data.processed.PIndicatorGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SideBarAdapter extends ArrayAdapter<PIndicatorGroup> {
    private Context _context;
    private boolean[] _on;
    public SideBarAdapter(Context context, int resource, List<PIndicatorGroup> objects) {
        super(context, resource, objects);
        _context = context;
        _on = new boolean[objects.size()];
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        PIndicatorGroup indicator = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.side_bar_item, parent, false);
        }

        List<Class> set = new ArrayList<>(indicator.getIndicators().values());
        SideBarItemAdapter indicatorAdapter = new SideBarItemAdapter(_context, android.R.layout.simple_list_item_1, set);
        ListView listView = (ListView) convertView.findViewById(R.id.side_item_list);
        listView.setAdapter(indicatorAdapter);

        Switch group_name = (Switch) convertView.findViewById(R.id.group_name);
        group_name.setText(indicator.getName());

        group_name.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                _on[position] = isChecked;
            }
        });

        group_name.setChecked(_on[position]);

        return convertView;
    }
}
