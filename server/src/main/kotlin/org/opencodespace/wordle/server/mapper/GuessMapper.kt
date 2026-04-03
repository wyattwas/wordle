package org.opencodespace.wordle.server.mapper

import org.opencodespace.wordle.core.dto.GuessDTO
import org.opencodespace.wordle.server.entity.GuessEntity
import org.opencodespace.wordle.server.entity.UserEntity
import org.opencodespace.wordle.server.repository.UserRepository

fun GuessEntity.toDTO() = GuessDTO(
    id = this.id,
    date = this.date,
    index = this.index,
    word = this.word,
    points = this.points,
    isCorrect = this.isCorrect,
    userId = this.user.id
)

fun GuessDTO.toEntity(userRepository: UserRepository): GuessEntity? {
    val user = userRepository.findById(this.userId) ?: return null

    return GuessEntity(
        id = this.id,
        date = this.date,
        index = this.index,
        word = this.word,
        points = this.points,
        isCorrect = this.isCorrect,
        user = user
    )
}
