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
import java.util.List;

public class SideBarItemAdapter extends ArrayAdapter<Class> {
    private boolean[] _checkboxes;
    private int[] _sliders;

    public SideBarItemAdapter(Context context, int resource, List<Class> items) {
        super(context, resource, items);
        _checkboxes = new boolean[items.size()];
        _sliders = new int[items.size()];
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Class indicator = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.side_bar_sub_item, parent, false);
        }

        TextView item_id = (TextView) convertView.findViewById(R.id.item_id);
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

        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                _checkboxes[position] = isChecked;

                if (isChecked){
                    seeker_layout.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
                } else {
                    params.height = 0;
                }
                seeker_layout.requestLayout();
            }
        });

        final TextView slider_value = (TextView) convertView.findViewById(R.id.slider_value);
        SeekBar seekbar = (SeekBar) convertView.findViewById(R.id.item_slider);
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
                _sliders[position] = seekBar.getProgress();
            }
        });

        seekbar.setProgress(_sliders[position]);
        checkbox.setChecked(_checkboxes[position]);

        return convertView;
    }
}
