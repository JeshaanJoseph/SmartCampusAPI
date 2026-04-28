package com.mycompany.smartcampusapi.resources;

import com.mycompany.smartcampusapi.dao.*;
import com.mycompany.smartcampusapi.exceptions.LinkedResourceNotFoundException;
import com.mycompany.smartcampusapi.model.*;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("/sensors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SensorResource {

    @GET
    public Object get(@QueryParam("type") String type) {
        if (type != null) return SensorDAO.getByType(type);
        return SensorDAO.getAll();
    }

    @POST
    public Response create(Sensor s) {

        if (RoomDAO.get(s.getRoomId()) == null) {
            throw new LinkedResourceNotFoundException("Room not found");
        }

        SensorDAO.add(s);
        RoomDAO.get(s.getRoomId()).getSensorIds().add(s.getId());

        return Response.status(201).entity(s).build();
    }

    @GET
    @Path("/{id}")
    public Sensor getOne(@PathParam("id") String id) {
        return SensorDAO.get(id);
    }

    // SUB-RESOURCE LOCATOR
    @Path("/{id}/readings")
    public SensorReadingResource readings() {
        return new SensorReadingResource();
    }
}