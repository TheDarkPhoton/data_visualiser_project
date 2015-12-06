package com.darkphoton.data_visualiser_project;

/**
 * Used for ListView with CheckBoxes
 */
public class Model {
    String name;
    boolean enabled;

    Model(String name, boolean value){
        this.name = name;
        this.enabled = value;
    }
    public String getName(){
        return this.name;
    }
    public boolean isEnabled(){
        return this.enabled;
    }
    public void setEnabled(boolean b){
        enabled = b;
    }
}
