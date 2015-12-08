package com.darkphoton.data_visualiser_project;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
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

        LinearLayout layout = ((LinearLayout) convertView);

        LayoutInflater inflater = ((LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE));
        for (Class i : indicator.getIndicators().values()) {
            LinearLayout sub_item = ((LinearLayout) inflater.inflate(R.layout.side_bar_sub_item, null));

            layout.addView(sub_item);
        }

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
