package com.mycompany.smartcampusapi.mappers;

import com.mycompany.smartcampusapi.exceptions.LinkedResourceNotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.*;

@Provider
public class LinkedMapper implements ExceptionMapper<LinkedResourceNotFoundException> {
    public Response toResponse(LinkedResourceNotFoundException e) {
        return Response.status(422).entity(e.getMessage()).build();
    }
}