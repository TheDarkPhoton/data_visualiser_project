    package com.darkphoton.data_visualiser_project;

    import android.graphics.Color;
    import android.net.Uri;
    import android.support.v7.app.AppCompatActivity;
    import android.os.Bundle;
    import android.view.Menu;
    import android.view.MenuItem;
    import android.view.ViewGroup;
    import android.widget.RelativeLayout;
    import android.widget.TextView;

    import com.github.mikephil.charting.charts.LineChart;
    import com.github.mikephil.charting.components.Legend;
    import com.github.mikephil.charting.components.Legend.LegendForm;
    import com.github.mikephil.charting.components.XAxis;
    import com.github.mikephil.charting.components.YAxis;
    import com.github.mikephil.charting.data.Entry;
    import com.github.mikephil.charting.data.LineData;
    import com.github.mikephil.charting.data.LineDataSet;
    import com.github.mikephil.charting.utils.ColorTemplate;
    import com.google.android.gms.appindexing.Action;
    import com.google.android.gms.appindexing.AppIndex;
    import com.google.android.gms.common.api.GoogleApiClient;

    import java.util.ArrayList;

    public class MainActivity extends AppCompatActivity {

        private LineChart lineChart;
        private RelativeLayout layout;

        private TextView xAxisLabel;
        private TextView yAxisLabel;

        private Integer maxYValue;
        private Integer minYValue;
        /**
         * ATTENTION: This was auto-generated to implement the App Indexing API.
         * See https://g.co/AppIndexing/AndroidStudio for more information.
         */
        private GoogleApiClient client;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            drawLineGraph();

            // ATTENTION: This was auto-generated to implement the App Indexing API.
            // See https://g.co/AppIndexing/AndroidStudio for more information.
            client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        }


        public void drawLineGraph(){

            layout = (RelativeLayout) findViewById(R.id.layout);

            //customising the X Axis Title
            xAxisLabel = (TextView) findViewById(R.id.xAxis);
            xAxisLabel.setText("Year");
            xAxisLabel.setTextSize(20f);
            xAxisLabel.setTextColor(Color.BLACK);

            //customising the Y Axis Title
            yAxisLabel = (TextView) findViewById(R.id.yAxis);
            yAxisLabel.setText("Value");
            yAxisLabel.setTextSize(20f);
            yAxisLabel.setTextColor(Color.BLACK);

            //create line chart
            lineChart = new LineChart(this);

            RelativeLayout.LayoutParams relativeParams = new RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            layout.addView(lineChart, relativeParams);

            //line chart customisation
            lineChart.setDescription("");
            lineChart.setNoDataTextDescription("No Data Provided");

            // enable touch gesture
            lineChart.setTouchEnabled(true);

            //enable scaling
            lineChart.setDragEnabled(true);
            lineChart.setScaleEnabled(true);
            lineChart.setDrawGridBackground(false);

            //pinch zoom enabled to prevent x and y axis scaling separately
            lineChart.setPinchZoom(true);

            //setting the background colour of the chart
            lineChart.setBackgroundColor(Color.WHITE);

            //setting up the data for the chart
            LineData data = new LineData();
            data.setValueTextColor(Color.LTGRAY);

            //adding the data to the line chart
            lineChart.setData(data);

            //getting and customising the legend
            Legend legend = lineChart.getLegend();
            legend.setForm(LegendForm.LINE);
            legend.setTextColor(Color.LTGRAY);

            XAxis xAxis = lineChart.getXAxis();
            xAxis.setTextColor(Color.BLACK);
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setDrawAxisLine(false);
            xAxis.setAvoidFirstLastClipping(true);

            YAxis yAxisl = lineChart.getAxisLeft();
            yAxisl.setTextColor(Color.BLACK);
//                yAxisl.setAxisMinValue();
            yAxisl.setDrawAxisLine(true);

            YAxis yAxisr = lineChart.getAxisRight();
            yAxisr.setEnabled(false);

            //setting the values for the line graph
            setDataValues();

        }

        //set the data for the line graph
        private void setDataValues() {

            //values for the X Axis
            ArrayList<String> xVals = new ArrayList<String>();
            //random some method for getting some values
            for (int i = 0; i < 10; i++) {

               if(i<10){ xVals.add("200" + (i) + "");}
                else{
                   xVals.add("20"+(i));
               }
            }

            //values for the Y axis
            ArrayList<Entry> yVals = new ArrayList<Entry>();
            //random method for getting some values
            for (int i = 0; i < 10; i++) {

                yVals.add(new Entry(i*i, i));
            }

            // create the dataset and give it a type
            LineDataSet set1 = new LineDataSet(yVals, "Value");
            set1.setDrawCubic(true);
            set1.setCubicIntensity(0.2f);
            set1.setAxisDependency(YAxis.AxisDependency.LEFT);
            set1.setColor(ColorTemplate.getHoloBlue());
            set1.setCircleColor(ColorTemplate.getHoloBlue());
            set1.setLineWidth(2f);
            set1.setCircleSize(3f);
            set1.setFillAlpha(65);
            set1.setFillColor(ColorTemplate.getHoloBlue());
            set1.setHighLightColor(Color.rgb(244, 117, 117));
            set1.setDrawCircleHole(true);
            set1.setCircleColorHole(Color.WHITE);

            // create a data object with the dataset
            LineData data = new LineData(xVals, set1);
            data.setValueTextColor(Color.BLACK);
            data.setValueTextSize(9f);

            // set data
            lineChart.setData(data);
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


        @Override
        public void onStart() {
            super.onStart();

            // ATTENTION: This was auto-generated to implement the App Indexing API.
            // See https://g.co/AppIndexing/AndroidStudio for more information.
            client.connect();
            Action viewAction = Action.newAction(
                    Action.TYPE_VIEW, // TODO: choose an action type.
                    "Main Page", // TODO: Define a title for the content shown.
                    // TODO: If you have web page content that matches this app activity's content,
                    // make sure this auto-generated web page URL is correct.
                    // Otherwise, set the URL to null.
                    Uri.parse("http://host/path"),
                    // TODO: Make sure this auto-generated app deep link URI is correct.
                    Uri.parse("android-app://com.darkphoton.data_visualiser_project/http/host/path")
            );
            AppIndex.AppIndexApi.start(client, viewAction);
        }

        @Override
        public void onStop() {
            super.onStop();

            // ATTENTION: This was auto-generated to implement the App Indexing API.
            // See https://g.co/AppIndexing/AndroidStudio for more information.
            Action viewAction = Action.newAction(
                    Action.TYPE_VIEW, // TODO: choose an action type.
                    "Main Page", // TODO: Define a title for the content shown.
                    // TODO: If you have web page content that matches this app activity's content,
                    // make sure this auto-generated web page URL is correct.
                    // Otherwise, set the URL to null.
                    Uri.parse("http://host/path"),
                    // TODO: Make sure this auto-generated app deep link URI is correct.
                    Uri.parse("android-app://com.darkphoton.data_visualiser_project/http/host/path")
            );
            AppIndex.AppIndexApi.end(client, viewAction);
            client.disconnect();
        }
    }
