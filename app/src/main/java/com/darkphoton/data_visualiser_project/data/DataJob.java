package com.darkphoton.data_visualiser_project.data;

import com.darkphoton.data_visualiser_project.data.raw.DataCache;

public interface DataJob {
    void run(DataCache cache);
}
