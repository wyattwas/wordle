package org.opencodespace.wordle.core.dto

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class WordleDTO @JsonCreator constructor(
    @JsonProperty("id") val id: Long,
    @JsonProperty("solution") val solution: String,
    @JsonProperty("print_date") val printDate: String,
    @JsonProperty("days_since_launch") val daysSinceLaunch: Int,
    @JsonProperty("editor") val editor: String
)
