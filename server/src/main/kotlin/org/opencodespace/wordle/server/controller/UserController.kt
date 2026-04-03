package org.opencodespace.wordle.server.controller

import jakarta.inject.Inject
import jakarta.ws.rs.BeanParam
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.DELETE
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.PUT
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType.APPLICATION_JSON
import jakarta.ws.rs.core.Response
import org.opencodespace.wordle.core.dto.UserDTO
import org.opencodespace.wordle.core.filters.UserFilter
import org.opencodespace.wordle.core.services.UserService

@Path("/user")
class UserController {
    @Inject private lateinit var userService: UserService

    @GET
    @Produces(APPLICATION_JSON)
    @Path("/{id}")
    fun getUser(@PathParam("id") id: Long): Response {
        val user = userService.getUserById(id) ?: return Response.status(Response.Status.NOT_FOUND).build()
        return Response.ok(user).build()
    }

    @GET
    @Produces(APPLICATION_JSON)
    fun getUsers(@BeanParam filter: UserFilter) = userService.getUsers(filter)

    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    fun createUser(user: UserDTO): Response {
        val createdUser = userService.createUser(user)
        return Response.status(Response.Status.CREATED).entity(createdUser).build()
    }

    @PUT
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @Path("/{id}")
    fun updateUser(@PathParam("id") id: Long, user: UserDTO): Response {
        val updatedUser = userService.updateUser(id, user) ?: return Response.status(Response.Status.NOT_FOUND).build()
        return Response.ok(updatedUser).build()
    }

    @DELETE
    @Path("/{id}")
    fun deleteUser(@PathParam("id") id: Long): Response {
        val isDeleted: Boolean = userService.deleteUser(id)
        if (!isDeleted) return Response.status(Response.Status.NOT_FOUND).build()
        return Response.status(Response.Status.NO_CONTENT).build()
    }
}
