package org.opencodespace.wordle.client.sdk.service

import org.opencodespace.wordle.core.dto.GuessDTO
import org.opencodespace.wordle.core.filters.GuessFilter

interface GuessService {
    fun getGuessById(id: Long): GuessDTO?

    fun getGuesses(): List<GuessDTO>

    fun getGuesses(filter: GuessFilter): List<GuessDTO>

    fun createGuess(guess: GuessDTO): GuessDTO

    fun updateGuess(id: Long, guess: GuessDTO): GuessDTO

    fun deleteGuess(id: Long): Boolean
}
