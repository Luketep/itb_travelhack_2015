package com.aakhmerov.thack.api.service.tos;

import java.util.List;

/**
 * Created by aakhmerov on 28.02.15.
 */
public class SearchResultTO {
    private String source;
    private String weekend;
    private String date;
    private List<DestinationTO> destinations;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getWeekend() {
        return weekend;
    }

    public void setWeekend(String weekend) {
        this.weekend = weekend;
    }

    public List<DestinationTO> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<DestinationTO> destinations) {
        this.destinations = destinations;
    }
}
