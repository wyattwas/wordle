package org.opencodespace.wordle.server.service

import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import org.opencodespace.wordle.core.dto.GuessDTO
import org.opencodespace.wordle.core.filters.GuessFilter
import org.opencodespace.wordle.core.services.GuessService
import org.opencodespace.wordle.server.entity.GuessEntity
import org.opencodespace.wordle.server.mapper.toDTO
import org.opencodespace.wordle.server.mapper.toEntity
import org.opencodespace.wordle.server.repository.GuessRepository
import org.opencodespace.wordle.server.repository.UserRepository

@ApplicationScoped
class GuessService : GuessService {
    @Inject
    private lateinit var guessRepository: GuessRepository

    @Inject
    private lateinit var userRepository: UserRepository

    override fun getGuessById(id: Long) = guessRepository.findById(id).toDTO()

    override fun getGuesses() = guessRepository.listAll().map { it.toDTO() }

    override fun getGuesses(filter: GuessFilter): List<GuessDTO> {
        return guessRepository.listAll().filter { filter.date == null || it.date == filter.date }
            .filter { filter.index == null || it.index == filter.index }
            .filter { filter.word == null || it.word == filter.word }
            .filter { filter.points == null || it.points == filter.points }
            .filter { filter.correct == null || it.isCorrect == filter.correct }
            .filter { filter.userId == null || it.user.id == filter.userId }.map { it.toDTO() }
    }

    override fun createGuess(guess: GuessDTO): GuessDTO? {
        val entity = guess.toEntity(userRepository)
        guessRepository.persist(entity)
        return entity?.toDTO()
    }

    override fun updateGuess(id: Long, guess: GuessDTO): GuessDTO? {
        val entity: GuessEntity? = guessRepository.findById(id)
        if (entity == null) {
            throw RuntimeException("Guess not found")
        }

        entity.date = guess.date
        entity.index = guess.index
        entity.points = guess.points
        entity.word = guess.word
        entity.isCorrect = guess.isCorrect
        entity.user = userRepository.findById(guess.userId)
        return entity.toDTO()
    }

    override fun deleteGuess(id: Long) = guessRepository.deleteById(id)
}
