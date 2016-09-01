package com.redcute.fetchsensorsdata;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.redcute.fetchsensorsdata.GsonObjects.GsonProject;

import java.util.ArrayList;

/**
 * Created by obrusvit on 31.8.16.
 */
public class ProjectAdapter extends ArrayAdapter<GsonProject>{

    private static class ViewHolder{
        private TextView projectId;
        private TextView description;

    }

    public ProjectAdapter(Context context, ArrayList<GsonProject> projects) {
        super(context, 0, projects);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        GsonProject project = getItem(position);

        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_layout, parent, false);
            viewHolder.projectId = (TextView) convertView.findViewById(R.id.listItemProjectId);
            viewHolder.description = (TextView) convertView.findViewById(R.id.listItemProjectDescription);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }


        viewHolder.projectId.setText(project.getProjectId());
        viewHolder.description.setText(project.getDescription());


        return convertView;


    }
}
