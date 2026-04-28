package com.mycompany.smartcampusapi.resources;

import com.mycompany.smartcampusapi.dao.SensorDAO;
import com.mycompany.smartcampusapi.model.*;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SensorReadingResource {

    @GET
    public Object get(@PathParam("id") String id) {
        return SensorDAO.get(id).getReadings();
    }

    @POST
    public Response add(@PathParam("id") String id, Reading r) {

        Sensor s = SensorDAO.get(id);

        s.getReadings().add(r);
        s.setCurrentValue(r.getValue());

        return Response.status(201).entity(r).build();
    }
}