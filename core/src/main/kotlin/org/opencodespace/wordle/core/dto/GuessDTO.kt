package org.opencodespace.wordle.core.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate

data class GuessDTO(
    @JsonProperty("id") val id: Long,
    @JsonProperty("date") val date: LocalDate,
    @JsonProperty("index") val index: Int,
    @JsonProperty("word") val word: String,
    @JsonProperty("points") val points: Int,
    @JsonProperty("is_correct") val isCorrect: Boolean,
    @JsonProperty("user_id") val userId: Long
)
