package com.redcute.fetchsensorsdata.GsonObjects;

import java.util.ArrayList;

/**
 * Created by obrusvit on 31.8.16.
 */
public class GsonProjectArray {

    ArrayList<GsonProject> records;

    public ArrayList<GsonProject> getRecords() {
        return records;
    }

    public int getCount(){
        return records.size();
    }


}
