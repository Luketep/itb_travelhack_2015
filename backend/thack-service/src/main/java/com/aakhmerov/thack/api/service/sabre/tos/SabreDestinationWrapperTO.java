package com.aakhmerov.thack.api.service.sabre.tos;

import java.util.List;

/**
 * Created by aakhmerov on 28.02.15.
 */
public class SabreDestinationWrapperTO {
    private String OriginLocation;
    private List<SabreDestinationTO> FareInfo;

    public String getOriginLocation() {
        return OriginLocation;
    }

    public void setOriginLocation(String originLocation) {
        OriginLocation = originLocation;
    }

    public List<SabreDestinationTO> getFareInfo() {
        return FareInfo;
    }

    public void setFareInfo(List<SabreDestinationTO> fareInfo) {
        FareInfo = fareInfo;
    }
}
