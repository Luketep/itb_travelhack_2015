package com.aakhmerov.thack.api.rest;

import com.aakhmerov.thack.api.service.SearchService;

import com.aakhmerov.thack.api.service.tos.aggregated.SearchResultTO;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by aakhmerov on 28.02.15.
 */
@Path("/search")
@Component
public class SearchRestService {
    private static final String TXL = "TXL";
    @Autowired
    private SearchService searchService;

    /**
     * Check that all internals work fine together, through the status service
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/{from}/{date}")
    public SearchResultTO getStatus(@PathParam("from") String from,@PathParam("date") String date) {
        DateTime parsedDate = DateTime.parse(date);
//      TODO: replace with real source mapping
        return this.searchService.find(TXL, parsedDate);
    }
}
