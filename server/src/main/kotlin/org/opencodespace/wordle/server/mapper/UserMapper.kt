package org.opencodespace.wordle.server.mapper

import org.opencodespace.wordle.core.dto.UserDTO
import org.opencodespace.wordle.server.entity.UserEntity

fun UserEntity.toDTO() = UserDTO(
    id = this.id,
    name = this.name,
    handle = this.handle,
    platform = this.platform,
    score = this.score,
)

fun UserDTO.toEntity() = UserEntity(
    id = this.id ?: 0,
    name = this.name,
    handle = this.handle,
    platform = this.platform,
    score = this.score,
)
