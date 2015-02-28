package com.aakhmerov.thack.api.service.tos;

/**
 * Created by aakhmerov on 28.02.15.
 */
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
