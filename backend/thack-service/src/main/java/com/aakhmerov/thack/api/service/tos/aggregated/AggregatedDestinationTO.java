package com.aakhmerov.thack.api.service.tos.aggregated;

import com.aakhmerov.thack.api.service.gyg.tos.GygDataTO;
import com.aakhmerov.thack.api.service.gyg.tos.TourTO;
import com.aakhmerov.thack.api.service.sabre.tos.SabreDestinationTO;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by aakhmerov on 28.02.15.
 */
@XmlRootElement
public class AggregatedDestinationTO {
    private SabreDestinationTO sabreInfo;
    private List<TourTO> tours;

    public SabreDestinationTO getSabreInfo() {
        return sabreInfo;
    }

    public void setSabreInfo(SabreDestinationTO sabreInfo) {
        this.sabreInfo = sabreInfo;
    }

    public List<TourTO> getTours() {
        return tours;
    }

    public void setTours(List<TourTO> tours) {
        this.tours = tours;
    }
}
