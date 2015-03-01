package com.aakhmerov.thack.api.service.lh.tos;

/**
 * Created by aakhmerov on 01.03.15.
 */
public class FareTO {
    private String FBC;
    private String Carrier;
    private String Cabin;
    private String Orig;
    private String Dest;


    public String getFBC() {
        return FBC;
    }

    public void setFBC(String FBC) {
        this.FBC = FBC;
    }

    public String getCarrier() {
        return Carrier;
    }

    public void setCarrier(String carrier) {
        Carrier = carrier;
    }

    public String getCabin() {
        return Cabin;
    }

    public void setCabin(String cabin) {
        Cabin = cabin;
    }

    public String getOrig() {
        return Orig;
    }

    public void setOrig(String orig) {
        Orig = orig;
    }

    public String getDest() {
        return Dest;
    }

    public void setDest(String dest) {
        Dest = dest;
    }
}
