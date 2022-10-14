package com.demo

import com.demo.domain.Users
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import javax.ws.rs.core.MediaType.APPLICATION_JSON
import javax.ws.rs.core.MediaType.TEXT_PLAIN
import javax.ws.rs.core.Response

@Path("/hello")
class GreetingResource {

    val users = listOf(Users("Aemson", "Oslo", 31), Users("Jonathan", "Oslo", 2))

    @GET
    @Produces(TEXT_PLAIN)
    fun hello(@QueryParam(value = "name") name: String) = "Hello from RESTEasy Reactive $name"

    @GET
    @Produces(APPLICATION_JSON)
    fun getData(@QueryParam(value = "name") name: String): Response {
        val user = users.first { it.name == name }
        return Response.ok().entity(user).build()
    }
}
