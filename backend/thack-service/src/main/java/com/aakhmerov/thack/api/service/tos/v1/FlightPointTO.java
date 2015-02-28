package com.aakhmerov.thack.api.service.tos.v1;

import org.joda.time.DateTime;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by aakhmerov on 28.02.15.
 *
 */
@XmlRootElement
public class FlightPointTO {
    private String duration;
    private DateTime date;
    private String departureTime;
    private String arrival;
    private Long price;
    private List<FlightOptionTO> options;


    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public List<FlightOptionTO> getOptions() {
        return options;
    }

    public void setOptions(List<FlightOptionTO> options) {
        this.options = options;
    }
}
