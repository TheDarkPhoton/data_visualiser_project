package com.darkphoton.data_visualiser_project;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.darkphoton.data_visualiser_project.data.JSONDownloader;
import com.darkphoton.data_visualiser_project.data.DataJob;
import com.darkphoton.data_visualiser_project.data.Processor;
import com.darkphoton.data_visualiser_project.data.Cache;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TextView txtData;
    //http://api.worldbank.org/countries/indicators/NY.GDP.MKTP.CD?date=2010:2015&format=json&per_page=10000
    //http://api.worldbank.org/countries/indicators/SH.STA.ACSN?date=2010:2015&format=json&per_page=10000
    //http://api.worldbank.org/countries/indicators/SH.STA.ACSN.RU?date=2010:2015&format=json&per_page=10000
    //http://api.worldbank.org/countries/indicators/SH.STA.ACSN.UR?date=2010:2015&format=json&per_page=10000
    //http://api.worldbank.org/countries/indicators/SH.H2O.SAFE.ZS?date=2010:2015&format=json&per_page=10000
    //http://api.worldbank.org/countries/indicators/SH.H2O.SAFE.RU.ZS?date=2010:2015&format=json&per_page=10000
    //http://api.worldbank.org/countries/indicators/SH.H2O.SAFE.UR.ZS?date=2010:2015&format=json&per_page=10000

    //http://api.worldbank.org/countries/indicators/EG.NSF.ACCS.ZS?date=2010:2015&format=json&per_page=10000
    //http://api.worldbank.org/countries/indicators/EG.ELC.ACCS.ZS?date=2010:2015&format=json&per_page=10000
    //http://api.worldbank.org/countries/indicators/SH.ELC.SAFE.UR.ZS?date=2010:2015&format=json&per_page=10000
    //http://api.worldbank.org/countries/indicators/SH.ELC.SAFE.UR.ZS?date=2010:2015&format=json&per_page=10000

    private DataJob jsonJob = new DataJob() {
        @Override
        public void run(Cache cache) {
            Processor data = new Processor(cache);
            data.normalize();
            data.reduceToTop(5);
            txtData.setText(data.toString());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtData = (TextView)findViewById(R.id.txtData);
        JSONDownloader d = new JSONDownloader(this, jsonJob);
        d.execute("http://api.worldbank.org/countries/indicators/EG.ELC.ACCS.RU.ZS?date=2010:2015&format=json&per_page=10000");

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
