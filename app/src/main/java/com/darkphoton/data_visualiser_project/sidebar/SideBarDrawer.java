package com.darkphoton.data_visualiser_project.sidebar;

import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.darkphoton.data_visualiser_project.MainActivity;
import com.darkphoton.data_visualiser_project.R;
import com.darkphoton.data_visualiser_project.data.DataDownloader;
import com.darkphoton.data_visualiser_project.data.DataLoader;
import com.darkphoton.data_visualiser_project.data.processed.PIndicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class SideBarDrawer implements DrawerLayout.DrawerListener {
    private MainActivity _context;
    private DrawerLayout _drawer;
    private ListView _listView;

    public HashMap<String, Integer> _sliders = new HashMap<>();
    public boolean[] _checkboxes;

    public SideBarDrawer(MainActivity context){
        _context = context;

        _listView = (ListView) context.findViewById(R.id.side_list);

        List<Class> set = new ArrayList<>(PIndicator.indicatorClasses.values());
        SideBarItemAdapter indicatorAdapter = new SideBarItemAdapter(_context, android.R.layout.simple_list_item_1, set);
        _listView.setAdapter(indicatorAdapter);

        _drawer = (DrawerLayout) context.findViewById(R.id.drawer_layout);
        _drawer.setDrawerListener(this);

        _sliders = (HashMap<String, Integer>)SideBarItemAdapter.sliders.clone();
        _checkboxes = SideBarItemAdapter.checkboxes.clone();
    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {

    }

    @Override
    public void onDrawerOpened(View drawerView) {

    }

    @Override
    public void onDrawerClosed(View drawerView) {
        if (_sliders.equals(SideBarItemAdapter.sliders) && Arrays.equals(_checkboxes, SideBarItemAdapter.checkboxes))
            return;

        _sliders = (HashMap<String, Integer>)SideBarItemAdapter.sliders.clone();
        _checkboxes = SideBarItemAdapter.checkboxes.clone();

        final int firstItemPosition = _listView.getFirstVisiblePosition();
        final int lastItemPosition = firstItemPosition + _listView.getChildCount() - 1;

        ArrayList<String> ids = new ArrayList<>();

        for (int i = 0; i < _listView.getCount(); i++) {
            LinearLayout item;

            if (i < firstItemPosition || i > lastItemPosition ) {
                item = (LinearLayout) _listView.getAdapter().getView(i, null, _listView);
            } else {
                final int childIndex = i - firstItemPosition;
                item = (LinearLayout) _listView.getChildAt(childIndex);
            }

            CheckBox checkbox = (CheckBox) ((RelativeLayout) item.getChildAt(0)).getChildAt(1);
            //adds the id of selected indicator
            if (checkbox.isChecked()){
                ids.add(((TextView) ((RelativeLayout) item.getChildAt(0)).getChildAt(0)).getText().toString());
            }
        }

        if (_context.hasActiveInternetConnection() && MainActivity.rowData.isOutdated()){
            doOnline(ids);
        } else {
            doOffline(ids);
        }
    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }

    private void doOffline(ArrayList<String> ids) {
        if (ids.size() > 0) {
            DataLoader l = new DataLoader(_context, _context.offlineJob);
            l.execute(ids);
        }
    }

    private void doOnline(ArrayList<String> ids) {
        ArrayList<String> urls = new ArrayList<>();

        for (String id : ids) {
            urls.add("http://api.worldbank.org/countries/indicators/" + id + "?date=2010:2015&format=json&per_page=10000");
        }

        if (urls.size() > 0) {
            DataDownloader d = new DataDownloader(_context, _context.onlineJob);
            d.execute(urls);
        }
    }
}
