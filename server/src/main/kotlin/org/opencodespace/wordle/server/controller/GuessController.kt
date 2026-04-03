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
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.opencodespace.wordle.core.dto.GuessDTO
import org.opencodespace.wordle.core.filters.GuessFilter
import org.opencodespace.wordle.core.services.GuessService

@Path("/guess")
class GuessController {
    @Inject private lateinit var guessService: GuessService

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getGuesses(@BeanParam filter: GuessFilter) = guessService.getGuesses(filter)

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    fun getGuess(@PathParam("id") id: Long): Response {
        val guess = guessService.getGuessById(id) ?: return Response.status(Response.Status.NOT_FOUND).build()
        return Response.ok(guess).build()
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun createGuess(guess: GuessDTO): Response {
        val createdGuess = guessService.createGuess(guess) ?: return Response.status(Response.Status.BAD_REQUEST).build()
        return Response.status(Response.Status.CREATED).entity(createdGuess).build()
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    fun updateGuess(@PathParam("id") id: Long, guess: GuessDTO): Response {
        val updatedGuess = guessService.updateGuess(id, guess) ?: return Response.status(Response.Status.NOT_FOUND).build()
        return Response.ok(updatedGuess).build()
    }

    @DELETE
    @Path("/{id}")
    fun deleteGuess(@PathParam("id") id: Long): Response {
        val isDeleted = guessService.deleteGuess(id)
        if (!isDeleted) return Response.status(Response.Status.NOT_FOUND).build()
        return Response.status(Response.Status.NO_CONTENT).build()
    }
}
