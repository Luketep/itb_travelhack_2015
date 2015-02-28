package com.aakhmerov.thack.api.rest;


import com.aakhmerov.thack.api.domain.entities.Status;
import com.aakhmerov.thack.api.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created with IntelliJ IDEA.
 * User: aakhmerov
 * Date: 5/13/13
 * Time: 12:19 AM
 * To change this template use File | Settings | File Templates.
 */
@Path("/status")
@Component
public class StatusRestService {

    @Autowired
    private StatusService statusService;

    /**
     * Check that all internals work fine together, through the status service
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/ping")
    public Status getStatus() {
        return this.statusService.ping();
    }

}
