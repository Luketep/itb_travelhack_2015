package com.aakhmerov.thack.api.service;

import com.aakhmerov.thack.api.service.tos.SearchResultTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.apache.commons.io.IOUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.io.InputStream;

/**
 * Created by aakhmerov on 28.02.15.
 */
@Service
public class SearchService {
    private static final Logger logger = LoggerFactory.getLogger(SearchService.class);

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
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JodaModule());

        InputStream source = this.getClass().getClassLoader().getResourceAsStream("data/searchResult.json");
        SearchResultTO result = null;
        try {
            String sourceString = IOUtils.toString(source);
            result = om.readValue(sourceString,SearchResultTO.class);
        } catch (IOException e) {
            logger.error("cant get flights",e);
        }
        return result;
    }
}
