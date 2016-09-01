package com.redcute.fetchsensorsdata.GsonObjects;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by obrusvit on 30.8.16.
 */
public class GsonProject implements Parcelable{

    String projectId;
    String description;

    public String getProjectId() {
        return projectId;
    }

    public String getDescription() {
        return description;
    }



    protected GsonProject(Parcel in) {
        projectId = in.readString();
        description = in.readString();
    }

    public static final Creator<GsonProject> CREATOR = new Creator<GsonProject>() {
        @Override
        public GsonProject createFromParcel(Parcel in) {
            return new GsonProject(in);
        }

        @Override
        public GsonProject[] newArray(int size) {
            return new GsonProject[size];
        }
    };



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(projectId);
        dest.writeString(description);
    }
}
