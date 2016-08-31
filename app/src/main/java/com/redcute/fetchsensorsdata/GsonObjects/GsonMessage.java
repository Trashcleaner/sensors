package com.redcute.fetchsensorsdata.GsonObjects;

/**
 * Created by obrusvit on 30.8.16.
 */
public class GsonMessage {

    String createdAt; //standard ISO-8601, UTC
    String devEUI;
    int fPort;
    int fCntUp;
    int aDRBit;
    int fCntDn;
    String payloadHex;
    String micHex;
    float IrrRSSI;
    int spFast;
    String subBand;
    String channel;
    int devLrrCnt;
    String Irrid;
    float IrrLAT;
    float IrrLON;

    float[] Irrs;

    public float[] getIrrs() {
        return Irrs;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getDevEUI() {
        return devEUI;
    }

    public int getfPort() {
        return fPort;
    }

    public int getfCntUp() {
        return fCntUp;
    }

    public int getaDRBit() {
        return aDRBit;
    }

    public int getfCntDn() {
        return fCntDn;
    }

    public String getPayloadHex() {
        return payloadHex;
    }

    public String getMicHex() {
        return micHex;
    }

    public float getIrrRSSI() {
        return IrrRSSI;
    }

    public int getSpFast() {
        return spFast;
    }

    public String getSubBand() {
        return subBand;
    }

    public String getChannel() {
        return channel;
    }

    public int getDevLrrCnt() {
        return devLrrCnt;
    }

    public String getIrrid() {
        return Irrid;
    }

    public float getIrrLAT() {
        return IrrLAT;
    }

    public float getIrrLON() {
        return IrrLON;
    }

    public String getLonString(){
        return String.valueOf(IrrLON);
    }
    public String getLatString(){
        return String.valueOf(IrrLAT);
    }


}
