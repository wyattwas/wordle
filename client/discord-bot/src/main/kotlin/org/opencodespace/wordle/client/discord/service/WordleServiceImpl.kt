package org.opencodespace.wordle.client.discord.service

import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import org.eclipse.microprofile.rest.client.inject.RestClient
import org.opencodespace.wordle.client.discord.api.WordleApiClient
import org.opencodespace.wordle.client.sdk.service.WordleService
import org.opencodespace.wordle.core.dto.WordleDTO
import java.time.LocalDate

@ApplicationScoped
class WordleServiceImpl: WordleService {
    @Inject
    @RestClient
    private lateinit var wordleApiClient: WordleApiClient

    override fun getTodaysWordle(): WordleDTO {
        return wordleApiClient.getWordle(LocalDate.now().toString())
    }

    override fun getWordleByDate(date: LocalDate): WordleDTO {
        return wordleApiClient.getWordle(date.toString())
    }

}