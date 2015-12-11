package com.darkphoton.data_visualiser_project.sidebar;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.darkphoton.data_visualiser_project.R;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;

public class SideBarItemAdapter extends ArrayAdapter<Class> {
    public static HashMap<String, Integer> sliders = new HashMap<>();
    public static boolean[] checkboxes;

    public SideBarItemAdapter(Context context, int resource, List<Class> items) {
        super(context, resource, items);
        checkboxes = new boolean[items.size()];

        try {
            for (Class item : items) {
                Field id = item.getField("id");
                sliders.put((String) id.get(null), 100);
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Class indicator = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.side_bar_sub_item, parent, false);
        }

        final TextView item_id = (TextView) convertView.findViewById(R.id.item_id);
        CheckBox checkbox = (CheckBox) convertView.findViewById(R.id.item_checkbox);

        try {
            Field id = indicator.getField("id");
            item_id.setText((String) id.get(null));

            Field name = indicator.getField("name");
            checkbox.setText((String) name.get(null));

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        final LinearLayout seeker_layout = (LinearLayout) convertView.findViewById(R.id.slider_layout);
        final ViewGroup.LayoutParams params = seeker_layout.getLayoutParams();

        final TextView slider_value = (TextView) convertView.findViewById(R.id.slider_value);
        final SeekBar seekbar = (SeekBar) convertView.findViewById(R.id.item_slider);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                slider_value.setText(progress + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                sliders.put(item_id.getText().toString(), seekBar.getProgress());
            }
        });

        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkboxes[position] = isChecked;

                if (isChecked) {
                    seeker_layout.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
                } else {
                    seekbar.setProgress(100);
                    params.height = 0;
                }
                seeker_layout.requestLayout();
            }
        });

        seekbar.setProgress(sliders.get(item_id.getText().toString()));
        checkbox.setChecked(checkboxes[position]);

        return convertView;
    }
}
