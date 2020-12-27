package com.codecta.qoq;

import com.codecta.qoq.services.QoQServices;
import com.codecta.qoq.services.dto.GameMapDto;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;

@Path("/game")
public class QoQResource {


    @Inject
    QoQServices qoQServices;

    @GET
    @Path("/hi")
    @Produces(MediaType.APPLICATION_JSON)
    public Response hello(){
        return Response.ok().build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response start(){
        GameMapDto game = qoQServices.createMap();
        if(game != null)
            return Response.ok(game).build();
        return Response.noContent().build();
    }
}
