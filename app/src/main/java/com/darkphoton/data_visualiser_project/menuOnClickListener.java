package com.darkphoton.data_visualiser_project;

import android.view.View;
import android.widget.CheckBox;

/**
 * Created by Admin on 12/6/2015.
 */
public class menuOnClickListener implements View.OnClickListener {
    CheckBox checkBox;
    public menuOnClickListener(CheckBox checkBox){
        this.checkBox = checkBox;
    }
    @Override
    public void onClick(View v) {
        if (checkBox.isChecked()){
            checkBox.setChecked(false);
        }else{
            checkBox.setChecked(true);
        }
    }
}
