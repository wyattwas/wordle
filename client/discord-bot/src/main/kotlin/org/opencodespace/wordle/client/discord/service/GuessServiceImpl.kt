package org.opencodespace.wordle.client.discord.service

import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import org.eclipse.microprofile.rest.client.inject.RestClient
import org.opencodespace.wordle.client.discord.api.GuessApiClient
import org.opencodespace.wordle.client.sdk.service.GuessService
import org.opencodespace.wordle.core.dto.GuessDTO
import org.opencodespace.wordle.core.filters.GuessFilter

@ApplicationScoped
class GuessServiceImpl : GuessService {
    @Inject
    @RestClient
    private lateinit var guessApiClient: GuessApiClient

    override fun getGuessById(id: Long): GuessDTO {
        return guessApiClient.getGuess(id)
    }

    override fun getGuesses(): List<GuessDTO> {
        return guessApiClient.getGuesses()
    }

    override fun getGuesses(filter: GuessFilter): List<GuessDTO> {
        return guessApiClient.getGuesses(filter)
    }

    override fun createGuess(guess: GuessDTO): GuessDTO {
        return guessApiClient.createGuess(guess)
    }

    override fun updateGuess(id: Long, guess: GuessDTO): GuessDTO {
        return guessApiClient.updateGuess(id, guess)
    }

    override fun deleteGuess(id: Long): Boolean {
        return guessApiClient.deleteGuess(id)
    }
}