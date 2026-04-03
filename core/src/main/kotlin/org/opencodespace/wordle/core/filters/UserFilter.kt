package org.opencodespace.wordle.core.filters

import jakarta.ws.rs.QueryParam
import org.opencodespace.wordle.core.models.Platform

class UserFilter {
    @QueryParam("name") var name: String? = null
    @QueryParam("handle") var handle: String? = null
    @QueryParam("platform") var platform: Platform? = null
    @QueryParam("score") var score: Int? = null
}
