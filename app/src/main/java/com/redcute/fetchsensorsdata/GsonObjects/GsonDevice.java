package com.redcute.fetchsensorsdata.GsonObjects;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by obrusvit on 30.8.16.
 */
public class GsonDevice implements Parcelable{

    String projectId;
    String devEUI;
    String description;
    String model;
    String vendor;

    protected GsonDevice(Parcel in) {
        projectId = in.readString();
        devEUI = in.readString();
        description = in.readString();
        model = in.readString();
        vendor = in.readString();
    }

    public static final Creator<GsonDevice> CREATOR = new Creator<GsonDevice>() {
        @Override
        public GsonDevice createFromParcel(Parcel in) {
            return new GsonDevice(in);
        }

        @Override
        public GsonDevice[] newArray(int size) {
            return new GsonDevice[size];
        }
    };

    public String getProjectId() {
        return projectId;
    }

    public String getDevEUI() {
        return devEUI;
    }

    public String getDescription() {
        return description;
    }

    public String getModel() {
        return model;
    }

    public String getVendor() {
        return vendor;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(projectId);
        dest.writeString(devEUI);
        dest.writeString(description);
        dest.writeString(model);
        dest.writeString(vendor);
    }
}
