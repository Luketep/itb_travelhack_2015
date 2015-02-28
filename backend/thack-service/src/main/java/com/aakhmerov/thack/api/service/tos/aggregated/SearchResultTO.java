package com.aakhmerov.thack.api.service.tos.aggregated;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aakhmerov on 28.02.15.
 */
@XmlRootElement
public class SearchResultTO {
    private List<AggregatedDestinationTO> destinations = new ArrayList<AggregatedDestinationTO>();

    public List<AggregatedDestinationTO> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<AggregatedDestinationTO> destinations) {
        this.destinations = destinations;
    }
}
