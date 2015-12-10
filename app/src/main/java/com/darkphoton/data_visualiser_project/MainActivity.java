package com.darkphoton.data_visualiser_project;

import android.content.Context;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.RelativeLayout;

import com.darkphoton.data_visualiser_project.data.DataJob;
import com.darkphoton.data_visualiser_project.data.Processor;
import com.darkphoton.data_visualiser_project.data.Cache;
import com.darkphoton.data_visualiser_project.partials.CountryList;
import com.darkphoton.data_visualiser_project.partials.InfoPartial;
import com.darkphoton.data_visualiser_project.partials.LineGraphPanel;
import com.darkphoton.data_visualiser_project.partials.PartialPanel;
import com.darkphoton.data_visualiser_project.partials.PieChartPanel;
import com.darkphoton.data_visualiser_project.sidebar.SideBarDrawer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static Point screen_size = new Point();
    public static InfoPartial infoPanel;
    public static PieChartPanel pieChartPanel;
    public static LineGraphPanel lineGraphPanel;
    public static PartialPanel activePanel = null;
    public static HorizontalScrollView countries;

    private SideBarDrawer sideBar;


    public DataJob jsonJob = new DataJob() {
        @Override
        public void run(Cache cache) {
            Processor data = new Processor(cache);
            data.normalize();
            data.reduceToTop(20);
            countries.removeAllViews();
            countries.addView(new CountryList(MainActivity.this, data));
        }
    };

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
        int index = ((ViewGroup) countries.getParent()).indexOfChild(countries) + 1;

//        ViewGroup drawer = (ViewGroup) findViewById(R.id.drawer_layout);
        ViewGroup drawer = (ViewGroup) findViewById(android.R.id.content);
        infoPanel = new InfoPartial(this);
        drawer.addView(infoPanel);
        pieChartPanel = new PieChartPanel(this);
        drawer.addView(pieChartPanel);
        lineGraphPanel = new LineGraphPanel(this);
        drawer.addView(lineGraphPanel);

        sideBar = new SideBarDrawer(this);

        /*Temporary test code, for everyone's benefit of understanding how the methods can be used.*/
        ArrayList testArraylist = new ArrayList();
        testArraylist.add("Bob");
        serialize(testArraylist, "testArrayList");

        ArrayList reconstructedArrayList = (ArrayList) deserialize("testArrayList");
        System.out.println(reconstructedArrayList.get(0));
        /*End of test code*/
    }

    /*This method will serialize the given object with the given filename in the /files directory
    * as a .ser file. You do not need to add any extensions to the filename.*/
    public void serialize(Object object, String filename){
        try{
            Context context = getBaseContext();
            FileOutputStream fileOutput = context.openFileOutput(filename+".ser", Context.MODE_PRIVATE);
            ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
            objectOutput.writeObject(object);
            objectOutput.close();
            fileOutput.close();
        }catch (IOException i){
            i.printStackTrace();
        }
    }

    /*This method will deserialize the object with the given filename. You do not need to specify the
    * file extension, the files are assumed to be .ser and in the /files directory, as if
    * created with the serialize() method. The deserialized object is returned. */
    public Object deserialize(String filename){
        Object object = null;
        try {
            FileInputStream fileInput = openFileInput(filename + ".ser");
            ObjectInputStream objectInput = new ObjectInputStream(fileInput);
            object = objectInput.readObject();
            objectInput.close();
            fileInput.close();
        }catch (IOException i){
            i.printStackTrace();
        }catch (ClassNotFoundException c){
            System.out.println(filename+" serialized object not found");
            c.printStackTrace();
        }
        return object;
    }

    /*This method is used for normalizing the percentages from the Education data indicators as all of
    * them are given in a percentage format. It takes an ArrayList of percentages given as Strings and
    * returns a single percentage as a String. All but one have been given as "% of GDP per Capita"
    * except for "Government expenditure on education" which is given as "% of GDP". While this could
    * be converted to "% of GDP per Capita" it seems somewhat redundant because the data indicators
     * range dramatically, from 23% to as much as 297%*/
    public static String normalizePercentages(ArrayList<String> inputArray){
        int sum = 0;
        for(String s: inputArray){
            sum+=Integer.parseInt(s);
        }
        return sum/inputArray.size()+"%";
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
