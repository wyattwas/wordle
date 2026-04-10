package org.opencodespace.wordle.client.discord.api

import jakarta.ws.rs.DELETE
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.PUT
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient
import org.opencodespace.wordle.core.dto.GuessDTO
import org.opencodespace.wordle.core.filters.GuessFilter

@RegisterRestClient
@Path("/guess")
interface GuessApiClient {
    @GET
    fun getGuesses(): List<GuessDTO>

    @GET
    fun getGuesses(filter: GuessFilter): List<GuessDTO>

    @GET
    @Path("/{id}")
    fun getGuess(@PathParam("id") id: Long): GuessDTO

    @POST
    fun createGuess(guess: GuessDTO): GuessDTO

    @PUT
    @Path("/{id}")
    fun updateGuess(@PathParam("id") id: Long, guess: GuessDTO): GuessDTO

    @DELETE
    @Path("/{id}")
    fun deleteGuess(@PathParam("id") id: Long): Boolean
}