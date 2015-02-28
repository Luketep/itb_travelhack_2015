package com.aakhmerov.thack.api.service.tos;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by aakhmerov on 28.02.15.
 */
@XmlRootElement
public class FlightTO {
    private FlightPointTO to;
    private FlightPointTO from;

    public FlightPointTO getTo() {
        return to;
    }

    public void setTo(FlightPointTO to) {
        this.to = to;
    }

    public FlightPointTO getFrom() {
        return from;
    }

    public void setFrom(FlightPointTO from) {
        this.from = from;
    }
}
