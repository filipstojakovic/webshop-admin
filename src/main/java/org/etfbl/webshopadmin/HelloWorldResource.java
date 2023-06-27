package org.etfbl.webshopadmin;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("/test")
public class HelloWorldResource {
    public static final String CLICHED_MESSAGE = "Hello World!";

    @GET
    @Produces("text/plain")
    public String getHello() {
        return CLICHED_MESSAGE;
    }
}
