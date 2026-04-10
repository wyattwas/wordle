package org.opencodespace.wordle.client.discord.utils

import org.opencodespace.wordle.core.dto.GuessDTO

fun List<GuessDTO>.toChart(wordle: String): String {
    val chart = StringBuilder()

    for (guessDto in this.sortedBy { it.index }) {
        val guess = guessDto.word.lowercase()
        val solution = wordle.lowercase()

        val row = Array(guess.length) { '⬜'.toString() }
        val solutionChars = solution.toMutableList()

        for (i in guess.indices) {
            if (guess[i] == solution[i]) {
                row[i] = "🟩"
                solutionChars[i] = '*'
            }
        }

        for (i in guess.indices) {
            if (row[i] == "🟩") continue

            val indexInSolution = solutionChars.indexOf(guess[i])
            if (indexInSolution != -1) {
                row[i] = "🟨"
                solutionChars[indexInSolution] = '*'
            }
        }

        chart.append(row.joinToString("")).append("\n")
    }

    val remaining = 6 - this.size
    repeat(remaining.coerceAtLeast(0)) {
        chart.append("⬛⬛⬛⬛⬛\n")
    }

    return chart.toString().trim()
}