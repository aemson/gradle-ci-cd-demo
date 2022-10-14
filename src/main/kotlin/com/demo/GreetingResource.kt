package com.demo

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import javax.ws.rs.core.MediaType.TEXT_PLAIN

@Path("/hello")
class GreetingResource {

    @GET
    @Produces(TEXT_PLAIN)
    fun hello(@QueryParam(value = "name") name: String) = "Hello from RESTEasy Reactive $name"
}
