package com.darkphoton.data_visualiser_project;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;

import com.darkphoton.data_visualiser_project.data.DataJob;
import com.darkphoton.data_visualiser_project.data.Processor;
import com.darkphoton.data_visualiser_project.data.Cache;
import com.darkphoton.data_visualiser_project.data.processed.PIndicator;
import com.darkphoton.data_visualiser_project.partials.CountryList;
import com.darkphoton.data_visualiser_project.partials.InfoPartial;
import com.darkphoton.data_visualiser_project.partials.LineGraphPanel;
import com.darkphoton.data_visualiser_project.partials.PartialPanel;
import com.darkphoton.data_visualiser_project.partials.PieChartPanel;
import com.darkphoton.data_visualiser_project.sidebar.SideBarDrawer;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedInputStream;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    public static Point screen_size = new Point();
    public static InfoPartial infoPanel;
    public static PieChartPanel pieChartPanel;
    public static LineGraphPanel lineGraphPanel;
    public static PartialPanel activePanel = null;
    public static HorizontalScrollView countries;
    public static Cache rowData = new Cache();

    private SideBarDrawer sideBar;

    private Processor data;

    public DataJob onlineJob = new DataJob() {
        @Override
        public void run(Cache cache) {
            rowData.updateDataCache(cache.getCountries());
            data = new Processor(cache);
            data.normalize();
            data.reduceToTop(20);

            Processor.serialize(MainActivity.this, data);

            countries.removeAllViews();
            countries.addView(new CountryList(MainActivity.this, data));
        }
    };

    public DataJob offlineJob = new DataJob() {
        @Override
        public void run(Cache cache) {
            data = new Processor(cache);
            data.normalize();
            data.reduceToTop(20);

            Processor.serialize(MainActivity.this, data);

            countries.removeAllViews();
            countries.addView(new CountryList(MainActivity.this, data));
        }
    };

    private Thread _backgroundDownloader = new Thread(new Runnable() {
        @Override
        public void run() {
            Cache tempCache = new Cache();
            try {
                for (String id : PIndicator.indicatorClasses.keySet()) {
                    Cache cache = new Cache();
                    String pageUrl = "http://api.worldbank.org/countries/indicators/" + id + "?date=2010:2015&format=json&per_page=10000";

                    JSONArray json = null;
                    do {
                        String path;
                        if (json == null)
                            path = pageUrl + "&page=1";
                        else
                            path = pageUrl + "&page=" + (json.getJSONObject(0).getInt("page") + 1);

                        StringBuilder stringBuilder = new StringBuilder();
                        URL data = new URL(path);
                        BufferedInputStream bis = new BufferedInputStream(data.openStream());

                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while((bytesRead = bis.read(buffer)) > 0) {
                            String text = new String(buffer, 0, bytesRead);
                            stringBuilder.append(text);
                        }
                        bis.close();

                        json = new JSONArray(stringBuilder.toString());
                        JSONArray indicators = json.getJSONArray(1);
                        for (int i = 0; i < indicators.length(); i++) {
                            cache.updateDataCache(indicators.getJSONObject(i));
                        }
                    } while (json.getJSONObject(0).getInt("page") < json.getJSONObject(0).getInt("pages"));

                    tempCache.updateDataCache(cache.getCountries());
                    Log.i("CACHE", "Loaded indicator " + id);
                }
            } catch (java.io.IOException | JSONException e) {
                e.printStackTrace();
                Log.i("CACHE", "Error");
                return;
            }

            tempCache.cachingCompleted();
            Cache.serialize(MainActivity.this, tempCache);
            MainActivity.rowData = tempCache;
            Log.i("CACHE", "Finished");

            Handler h = new Handler(MainActivity.this.getMainLooper());
            h.post(new Runnable() {
                @Override
                public void run() {
                    final AlertDialog.Builder alert  = new AlertDialog.Builder(MainActivity.this);
                    alert.setMessage("You can now use this application offline.");
                    alert.setTitle("Data Caching Completed");
                    alert.setPositiveButton("Ok", null);
                    alert.setCancelable(true);
                    alert.create().show();
                }
            });
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        Display display = getWindowManager().getDefaultDisplay();
        display.getSize(screen_size);

        countries = (HorizontalScrollView) findViewById(R.id.country_list);
        countries.addView(new CountryList(this));

        ViewGroup drawer = (ViewGroup) findViewById(android.R.id.content);
        infoPanel = new InfoPartial(this);
        drawer.addView(infoPanel);
        pieChartPanel = new PieChartPanel(this);
        drawer.addView(pieChartPanel);
        lineGraphPanel = new LineGraphPanel(this);
        drawer.addView(lineGraphPanel);

        sideBar = new SideBarDrawer(this);

        Cache tmpCache = Cache.deserialize(this);
        if (tmpCache != null)
            MainActivity.rowData = tmpCache;

        data = Processor.deserialize(this);
        if (data != null) {
            countries.removeAllViews();
            countries.addView(new CountryList(this, data));
        }

        if (hasActiveInternetConnection() && MainActivity.rowData.isOutdated()){
            _backgroundDownloader.start();
        } else if (MainActivity.rowData.getCountries().size() == 0) {
            final AlertDialog.Builder alert  = new AlertDialog.Builder(this);
            alert.setMessage("You must have internet access the first time you are using this application.");
            alert.setTitle("Error No Internet Access");
            alert.setPositiveButton("Quit",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            MainActivity.this.finishAffinity();
                        }
                    });
            alert.setCancelable(true);
            alert.create().show();
        }
    }

    public boolean hasActiveInternetConnection() {
        if (isNetworkAvailable()) {
            try {
                Process p1 = java.lang.Runtime.getRuntime().exec("ping -c 1    www.google.com");
                int returnVal = p1.waitFor();
                return ( returnVal == 0 );
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        return info != null;
    }

    @Override
    public void onBackPressed() {
        if (activePanel != null) {
            activePanel.close();
            activePanel = null;
            MainActivity.countries.setEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
