package com.mycompany.smartcampusapi.filters;

import javax.ws.rs.container.*;
import javax.ws.rs.ext.Provider;
import java.util.logging.Logger;

@Provider
public class LoggingFilter implements ContainerRequestFilter, ContainerResponseFilter {

    private static final Logger log = Logger.getLogger("API");

    public void filter(ContainerRequestContext req) {
        log.info(req.getMethod() + " " + req.getUriInfo().getRequestUri());
    }

    public void filter(ContainerRequestContext req, ContainerResponseContext res) {
        log.info("Status: " + res.getStatus());
    }
}