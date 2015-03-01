package com.aakhmerov.thack.api.service.lh.tos;

import java.util.List;

/**
 * Created by aakhmerov on 01.03.15.
 */
public class LHFlightSearchRequestTO {
    private String Response;
    private String User;
    private String Pass;
    private String Environment;
    private List<String> Origin;
    private List<String> Destination;
    private String DepartureFrom;
    private String LengthOfStay;

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String pass) {
        Pass = pass;
    }

    public String getEnvironment() {
        return Environment;
    }

    public void setEnvironment(String environment) {
        Environment = environment;
    }


    public String getResponse() {
        return Response;
    }

    public void setResponse(String response) {
        Response = response;
    }

    public List<String> getOrigin() {
        return Origin;
    }

    public void setOrigin(List<String> origin) {
        Origin = origin;
    }

    public List<String> getDestination() {
        return Destination;
    }

    public void setDestination(List<String> destination) {
        Destination = destination;
    }

    public String getDepartureFrom() {
        return DepartureFrom;
    }

    public void setDepartureFrom(String departureFrom) {
        DepartureFrom = departureFrom;
    }

    public String getLengthOfStay() {
        return LengthOfStay;
    }

    public void setLengthOfStay(String lengthOfStay) {
        LengthOfStay = lengthOfStay;
    }
}
