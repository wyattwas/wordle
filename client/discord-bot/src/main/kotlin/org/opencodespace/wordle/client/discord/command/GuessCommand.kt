package org.opencodespace.wordle.client.discord.command

import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import org.opencodespace.wordle.client.discord.utils.toChart
import org.opencodespace.wordle.client.sdk.command.Command
import org.opencodespace.wordle.client.sdk.command.NOT_REGISTERED_MESSAGE
import org.opencodespace.wordle.client.sdk.service.GuessService
import org.opencodespace.wordle.client.sdk.service.UserService
import org.opencodespace.wordle.client.sdk.service.WordleService
import org.opencodespace.wordle.core.dto.GuessDTO
import org.opencodespace.wordle.core.filters.GuessFilter
import java.time.LocalDate

@ApplicationScoped
class GuessCommand : ListenerAdapter() {
    @Inject private lateinit var wordleService: WordleService
    @Inject private lateinit var guessService: GuessService
    @Inject private lateinit var userService: UserService

    override fun onSlashCommandInteraction(event: SlashCommandInteractionEvent) {
        if (event.name != Command.GUESS.name.lowercase()) return

        val user = userService.getUserByHandle(event.user.id) ?: run {
            event.reply(NOT_REGISTERED_MESSAGE(DiscordCommandFormatter())).setEphemeral(true).queue()
            return
        }

        val guessWord = requireNotNull(event.getOption("guess")).asString.lowercase()
        if (guessWord.length != 5) {
            event.reply("Silly, it needs to be a 5 letter word").setEphemeral(true).queue()
            return
        }

        val guessFilter = GuessFilter()
        guessFilter.userId = user.id
        guessFilter.date = LocalDate.now()
        val guesses = guessService.getGuesses()

        val currentIndex = guesses.maxByOrNull { it.index }?.index
        val wordle = wordleService.getTodaysWordle()

        if (currentIndex == 5 || guesses.any { it.isCorrect }) {
            event.reply("You already did your wordle for today: ||${wordle.solution}||").setEphemeral(true).queue()
            return
        }

        val guess = GuessDTO(
            0,
            LocalDate.now(),
            currentIndex?.plus(1) ?: 0,
            guessWord.lowercase(),
            0,
            guessWord.lowercase() == wordle.solution.lowercase(),
            user.id!!,
        )

        guessService.createGuess(guess)
        val newGuessList: MutableList<GuessDTO> = mutableListOf()
        guesses.all { newGuessList.add(it) }
        newGuessList.add(guess)

        if (guess.isCorrect || currentIndex?.plus(1) == 5) {
            event.reply("${newGuessList.toChart(wordle.solution)}\nThe wordle was: ||${wordle.solution}||").setEphemeral(true).queue()
        } else event.reply(newGuessList.toChart(wordle.solution)).setEphemeral(true).queue()
    }
}