package com.darkphoton.data_visualiser_project;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.net.URL;

/**
 * Created by darkphoton on 25/11/15.
 */
public class JSONDownloader extends AsyncTask<String, String, Void> {
    private ProgressDialog _progressDialog;
    private JSONObject jsonData;
    private String _result;
    private Job _job;

    JSONDownloader(AppCompatActivity context, Job job){
        _progressDialog = new ProgressDialog(context);
        _result = "";
        _job = job;
    }

    @Override
    protected void onPreExecute() {
        _progressDialog.setMessage("Downloading your data...");
        _progressDialog.show();
        _progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface arg0) {
                JSONDownloader.this.cancel(true);
            }
        });
    }

    @Override
    protected Void doInBackground(String... params) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            URL data = new URL(params[0]);
            BufferedInputStream bis = new BufferedInputStream(data.openStream());

            byte[] buffer = new byte[1024];
            int bytesRead;
            while((bytesRead = bis.read(buffer)) > 0) {
                String text = new String(buffer, 0, bytesRead);
                stringBuilder.append(text);
            }
            bis.close();

            _result = stringBuilder.toString();
        } catch (java.io.IOException e) {
            Log.e("URLException", "Error: " + e.toString());
            _progressDialog.dismiss();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        try {
            _job.run(new JSONObject(_result));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        _progressDialog.dismiss();
    }
}
