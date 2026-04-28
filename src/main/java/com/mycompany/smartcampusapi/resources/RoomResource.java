package com.mycompany.smartcampusapi.resources;

import com.mycompany.smartcampusapi.dao.RoomDAO;
import com.mycompany.smartcampusapi.exceptions.RoomNotEmptyException;
import com.mycompany.smartcampusapi.model.Room;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("/rooms")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RoomResource {

    @GET
    public Object getAll() {
        return RoomDAO.getAll();
    }

    @POST
    public Response create(Room r) {
        RoomDAO.add(r);
        return Response.status(201).entity(r).build();
    }

    @GET
    @Path("/{id}")
    public Room get(@PathParam("id") String id) {
        return RoomDAO.get(id);
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") String id) {

        Room r = RoomDAO.get(id);

        if (!r.getSensorIds().isEmpty()) {
            throw new RoomNotEmptyException("Room has sensors");
        }

        RoomDAO.delete(id);
        return Response.ok().build();
    }
}