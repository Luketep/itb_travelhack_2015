package com.aakhmerov.thack.api.service.lh.tos;

import java.util.List;

/**
 * Created by aakhmerov on 01.03.15.
 */
public class JourneyTO {
    private PriceTO Price;
    private String PlatingCarrier;
    private List<FareTO> Fares;

    public PriceTO getPrice() {
        return Price;
    }

    public void setPrice(PriceTO price) {
        Price = price;
    }

    public String getPlatingCarrier() {
        return PlatingCarrier;
    }

    public void setPlatingCarrier(String platingCarrier) {
        PlatingCarrier = platingCarrier;
    }

    public List<FareTO> getFares() {
        return Fares;
    }

    public void setFares(List<FareTO> fares) {
        Fares = fares;
    }
}
