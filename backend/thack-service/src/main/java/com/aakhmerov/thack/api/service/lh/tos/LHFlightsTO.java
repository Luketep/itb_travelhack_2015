package com.aakhmerov.thack.api.service.lh.tos;

import java.util.List;

/**
 * Created by aakhmerov on 01.03.15.
 */
public class LHFlightsTO {
    private List<List<JourneyTO>> Journeys;

    public List<List<JourneyTO>> getJourneys() {
        return Journeys;
    }

    public void setJourneys(List<List<JourneyTO>> journeys) {
        Journeys = journeys;
    }
}
