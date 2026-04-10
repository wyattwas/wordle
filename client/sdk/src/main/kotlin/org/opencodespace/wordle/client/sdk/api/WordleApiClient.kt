package org.opencodespace.wordle.client.sdk.api

import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient
import org.opencodespace.wordle.core.dto.WordleDTO

@RegisterRestClient
interface WordleApiClient {
    @GET
    @Path("/{date}.json")
    fun getWordle(@PathParam("date") date: String): WordleDTO
}