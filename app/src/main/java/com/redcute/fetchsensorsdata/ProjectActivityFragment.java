package com.redcute.fetchsensorsdata;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.redcute.fetchsensorsdata.GsonObjects.GsonProject;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class ProjectActivityFragment extends ListFragment {

    final private static String TAG = "ProjectFragment";

    ArrayList<GsonProject> projects;


    public ProjectActivityFragment() {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Intent intent = getActivity().getIntent();
        projects = intent.getParcelableArrayListExtra(MainActivity.PROJECT_DATA_ARRAY);

        ProjectAdapter adapter = new ProjectAdapter(getActivity().getBaseContext(), projects);
        setListAdapter(adapter);

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        GsonProject project = (GsonProject) getListView().getItemAtPosition(position);
                    String stringUrl = "https://api.pripoj.me/device/get/"
                    +project.getProjectId()+
                    "?token=7TOEYELOrQpsJRhVQLRtnCaheigkWmX2";

        ConnectivityManager connMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            new FetchDataTask(getActivity().getApplication(), MainActivity.TASK_DEVICE).execute(stringUrl);

        } else {
            Toast.makeText(getContext(), "No internet", Toast.LENGTH_LONG).show();
        }


    }


}
