package com.darkphoton.data_visualiser_project.data;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.view.View;

import com.darkphoton.data_visualiser_project.MainActivity;

import java.util.ArrayList;

/**
 * Created by darkphoton on 11/12/15.
 */
public class DataLoader extends AsyncTask<ArrayList<String>, String, Void> {
    private MainActivity _context;
    private ProgressDialog _progressDialog;
    private Cache _dataCache = new Cache();
    private DataJob _job;

    public DataLoader(MainActivity context, DataJob job){
        _context = context;
        _progressDialog = new ProgressDialog(context);
        _job = job;
    }

    @Override
    protected void onPreExecute() {
        _progressDialog.setMessage("Loading your data...");
        _progressDialog.show();
        _progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface arg0) {
                DataLoader.this.cancel(true);
            }
        });
    }

    @Override
    protected Void doInBackground(ArrayList<String>... params) {
        _dataCache = MainActivity.rowData.subsetByIndicator(params[0]);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        _job.run(_dataCache);
        _progressDialog.dismiss();

        View decorView = _context.getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }
}
