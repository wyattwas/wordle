package org.opencodespace.wordle.core.services

import org.opencodespace.wordle.core.dto.WordleDTO
import java.time.LocalDate

interface WordleService {
    fun getTodaysWordle(): WordleDTO

    fun getWordleByDate(date: LocalDate): WordleDTO
}