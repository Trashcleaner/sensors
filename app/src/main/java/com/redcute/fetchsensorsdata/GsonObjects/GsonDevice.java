package com.redcute.fetchsensorsdata.GsonObjects;

/**
 * Created by obrusvit on 30.8.16.
 */
public class GsonDevice {

    String projectId;
    String devEUI;
    String description;
    String model;
    String vendor;

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
}
