package com.codecta.qoq;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;

@Path("/qoq")
public class QoQResource {

    @GET
    @Path("/hi")
    @Produces(MediaType.APPLICATION_JSON)
    public Response hello(){
        return Response.ok().build();
    }
}