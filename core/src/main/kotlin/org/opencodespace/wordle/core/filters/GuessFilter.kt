package org.opencodespace.wordle.core.filters

import jakarta.ws.rs.QueryParam
import java.time.LocalDate

class GuessFilter {
    @QueryParam("date") var date: LocalDate? = null
    @QueryParam("index") var index: Int? = null
    @QueryParam("word") var word: String? = null
    @QueryParam("points") var points: Int? = null
    @QueryParam("correct") var correct: Boolean? = null
    @QueryParam("user_id") var userId: Long? = null
}