package com.aakhmerov.thack.api.service.gyg.tos;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by aakhmerov on 28.02.15.
 */
@XmlRootElement
public class GygDataTO {
    private List<TourTO> tours;

    public List<TourTO> getTours() {
        return tours;
    }

    public void setTours(List<TourTO> tours) {
        this.tours = tours;
    }
}
