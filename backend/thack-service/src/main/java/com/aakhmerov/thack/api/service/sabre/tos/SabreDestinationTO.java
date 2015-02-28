package com.aakhmerov.thack.api.service.sabre.tos;

/**
 * Created by aakhmerov on 28.02.15.
 */
public class SabreDestinationTO {

    private String DestinationLocation;
    private String LowestNonStopFare;
    private String LowestFare;
    private String CurrencyCode;

    public String getDestinationLocation() {
        return DestinationLocation;
    }

    public void setDestinationLocation(String destinationLocation) {
        DestinationLocation = destinationLocation;
    }

    public String getLowestNonStopFare() {
        return LowestNonStopFare;
    }

    public void setLowestNonStopFare(String lowestNonStopFare) {
        LowestNonStopFare = lowestNonStopFare;
    }

    public String getLowestFare() {
        return LowestFare;
    }

    public void setLowestFare(String lowestFare) {
        LowestFare = lowestFare;
    }

    public String getCurrencyCode() {
        return CurrencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        CurrencyCode = currencyCode;
    }
}
