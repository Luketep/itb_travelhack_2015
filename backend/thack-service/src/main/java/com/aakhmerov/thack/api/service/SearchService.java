package com.aakhmerov.thack.api.service;

import com.aakhmerov.thack.api.service.gyg.GygService;
import com.aakhmerov.thack.api.service.gyg.tos.GygResponseTO;
import com.aakhmerov.thack.api.service.lh.LhService;
import com.aakhmerov.thack.api.service.lh.tos.LHFlightsTO;
import com.aakhmerov.thack.api.service.sabre.SabreService;
import com.aakhmerov.thack.api.service.sabre.tos.SabreDestinationTO;
import com.aakhmerov.thack.api.service.tos.aggregated.AggregatedDestinationTO;
import com.aakhmerov.thack.api.service.tos.aggregated.SearchResultTO;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * Created by aakhmerov on 28.02.15.
 */
@Service
public class SearchService {
    private static final Logger logger = LoggerFactory.getLogger(SearchService.class);

    @Autowired
    private SabreService sabreService;

    @Autowired
    private GygService gygService;

    @Autowired
    private LhService lhService;

    /**
     * Method to request weekend search
     *
     * 0. calculate desired dates
     * 1. request possible destinations with lowest price
     * 2. for each destination request possible attractions
     * 3. wrap results and give back
     *
     * @param from - City name or source airport
     * @param parsedDate - weekend date to start calculations from
     * @return - wrapping TO
     */
    public SearchResultTO find(String from, DateTime parsedDate) {
        SearchResultTO result = new SearchResultTO();
        DateTime departure = parsedDate;
        DateTime arrival = departure.plusDays(1);
        List<SabreDestinationTO> destinations = sabreService.getDestinations(from, departure, arrival);

        int i = 0;
        for (SabreDestinationTO destination : destinations) {
//          TODO: this is crap - get rid of it
            if (i == 3) {
                break;
            }
            AggregatedDestinationTO toAdd = new AggregatedDestinationTO();
            GygResponseTO wrappedTours = gygService.getTours(destination.getDestinationLocation());
            toAdd.setTours(wrappedTours.getData().getTours());
            toAdd.setSabreInfo(destination);
            result.getDestinations().add(toAdd);

            LHFlightsTO flights = lhService.getFlights(from,destination.getDestinationLocation(),departure);
            if (flights.getJourneys().size() > 0) {
                toAdd.setLHFlight(flights.getJourneys().get(0).get(0));
            }
            i = i+1;
        }
        return result;
    }
}
