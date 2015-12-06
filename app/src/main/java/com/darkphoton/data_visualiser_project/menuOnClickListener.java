package com.darkphoton.data_visualiser_project;

import android.view.View;
import android.widget.CheckBox;

/**
 * Created by Admin on 12/6/2015.
 */
public class menuOnClickListener implements View.OnClickListener {
    CheckBox checkBox;
    Model model;
    public menuOnClickListener(CheckBox checkBox, Model model){
        this.checkBox = checkBox;
        this.model = model;
    }
    @Override
    public void onClick(View v) {
        if (checkBox.isChecked()){
            checkBox.setChecked(false);
            model.setEnabled(false);
        }else{
            checkBox.setChecked(true);
        }
    }
}
