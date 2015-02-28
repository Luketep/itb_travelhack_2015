package com.aakhmerov.thack.api.service.tos.v1;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by aakhmerov on 28.02.15.
 */
@XmlRootElement
public class FlightOptionTO {
    private String name;
    private Long price;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
