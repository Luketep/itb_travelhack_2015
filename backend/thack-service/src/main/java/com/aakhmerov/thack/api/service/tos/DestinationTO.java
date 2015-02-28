package com.aakhmerov.thack.api.service.tos;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by aakhmerov on 28.02.15.
 */
@XmlRootElement
public class DestinationTO {

    private Long id;
    private String country;
    private String city;
    private FlightTO flight;
    private List<AttractionTO> events;
    private List<WeatherTO> weather;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public FlightTO getFlight() {
        return flight;
    }

    public void setFlight(FlightTO flight) {
        this.flight = flight;
    }

    public List<AttractionTO> getEvents() {
        return events;
    }

    public void setEvents(List<AttractionTO> events) {
        this.events = events;
    }


    public List<WeatherTO> getWeather() {
        return weather;
    }

    public void setWeather(List<WeatherTO> weather) {
        this.weather = weather;
    }
}
