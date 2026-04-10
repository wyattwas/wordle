package org.opencodespace.wordle.client.discord.api

import jakarta.ws.rs.*
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient
import org.opencodespace.wordle.core.dto.UserDTO
import org.opencodespace.wordle.core.filters.UserFilter

@RegisterRestClient
@Path("/user")
interface UserApiClient {
    @GET
    fun getUsers(): List<UserDTO>

    @GET
    fun getUsers(@BeanParam filter: UserFilter): List<UserDTO>

    @GET
    @Path("/{id}")
    fun getUserById(@PathParam("id") id: Long): UserDTO

    @POST
    fun createUser(userDTO: UserDTO): UserDTO

    @PUT
    @Path("/{id}")
    fun updateUser(@PathParam("id") id: Long, userDTO: UserDTO): UserDTO

    @DELETE
    @Path("/{id}")
    fun deleteUser(@PathParam("id") id: Long): Boolean
}