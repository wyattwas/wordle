package org.opencodespace.wordle.core.dto

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import org.opencodespace.wordle.core.models.Platform

data class UserDTO @JsonCreator constructor(
    @JsonProperty("id") val id: Long? = null,
    @JsonProperty("name") val name: String,
    @JsonProperty("handle") val handle: String,
    @JsonProperty("platform") val platform: Platform,
    @JsonProperty("score") val score: Int
)