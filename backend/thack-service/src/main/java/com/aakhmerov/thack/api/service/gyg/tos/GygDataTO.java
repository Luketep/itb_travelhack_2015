package com.aakhmerov.thack.api.service.gyg.tos;

import java.util.List;

/**
 * Created by aakhmerov on 28.02.15.
 */
public class GygDataTO {
    private List<TourTO> tours;

    public List<TourTO> getTours() {
        return tours;
    }

    public void setTours(List<TourTO> tours) {
        this.tours = tours;
    }
}
