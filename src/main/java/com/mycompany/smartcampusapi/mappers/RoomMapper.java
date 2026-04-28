package com.mycompany.smartcampusapi.mappers;

import com.mycompany.smartcampusapi.exceptions.RoomNotEmptyException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.*;

@Provider
public class RoomMapper implements ExceptionMapper<RoomNotEmptyException> {
    public Response toResponse(RoomNotEmptyException e) {
        return Response.status(409).entity(e.getMessage()).build();
    }
}