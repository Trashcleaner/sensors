package com.redcute.fetchsensorsdata;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.redcute.fetchsensorsdata.GsonObjects.GsonMetaData;
import com.redcute.fetchsensorsdata.GsonObjects.GsonProject;
import com.redcute.fetchsensorsdata.GsonObjects.GsonProjectArray;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "MainActivity";
    final public static String PROJECT_DATA_ARRAY = "ArrayOfGsonProjectObjects";
    final public static String DEVICE_DATA_ARRAY = "ArrayOfGsonDeviceObjects";
    final public static String MESSAGE_DATA_ARRAY = "ArrayOfGsonmessageObjects";



    final public static String TASK_PROJECT = "project";
    final public static String TASK_DEVICE = "device";
    final public static String TASK_MESSAGE = "message";


    private TextView mainText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mainText = (TextView) findViewById(R.id.main_text);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "I am here just for fun", Snackbar.LENGTH_LONG).show();
            }
        });

        Button button = (Button) findViewById(R.id.button);
        assert button != null;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringUrl = "https://api.pripoj.me/project/get/?token=7TOEYELOrQpsJRhVQLRtnCaheigkWmX2";
                ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    new FetchDataTask(getApplication(), MainActivity.TASK_PROJECT).execute(stringUrl);
                } else {
                    Snackbar.make(v, "Unable to connect", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });
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

    private class FetchProjectsData extends AsyncTask<String, Void, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mainText.setText("Downloading");
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                return downloadUrl(params[0]);
            }catch (IOException e){
                e.printStackTrace();
                return "Unable to fetch data";
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d(TAG, s);
            Gson gson = new Gson();
            GsonMetaData gsonMetaData = gson.fromJson(s, GsonMetaData.class);
            if("SUCCESS".equals(gsonMetaData.getStatus())) {
                //mainText.setText(gsonMetaData.getStatus());
                Toast.makeText(getApplicationContext(), "Fetch successful", Toast.LENGTH_LONG).show();
                GsonProjectArray gsonProjectArrayObject = gson.fromJson(s, GsonProjectArray.class);
                ArrayList<GsonProject> gsonProjectArray = gsonProjectArrayObject.getRecords();
                Intent intent = new Intent(getBaseContext(), ProjectActivity.class);
                intent.putParcelableArrayListExtra(MainActivity.PROJECT_DATA_ARRAY, gsonProjectArray);
                startActivity(intent);

            }else{
                Toast.makeText(getApplicationContext(), gsonMetaData.getStatus(), Toast.LENGTH_LONG).show();
            }
        }
    }

    // Given a URL, establishes an HttpUrlConnection and retrieves
// the web page content as a InputStream, which it returns as
// a string.
    private String downloadUrl(String stringUrl) throws IOException {
        InputStream is = null;

        try {
            URL url = new URL(stringUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            int fileSize = conn.getContentLength();
            Log.d(TAG, "The response is: " + response);
            is = conn.getInputStream();

            // Convert the InputStream into a string
            String contentAsString = readIt(is, fileSize);
            return contentAsString;

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    // Reads an InputStream and converts it to a String.
    public String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[len];
        reader.read(buffer);
        return new String(buffer);
    }
}
