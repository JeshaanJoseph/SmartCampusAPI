package com.mycompany.smartcampusapi.mappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.*;

@Provider
public class GlobalMapper implements ExceptionMapper<Throwable> {
    public Response toResponse(Throwable e) {
        return Response.status(500).entity("Internal Error").build();
    }
}