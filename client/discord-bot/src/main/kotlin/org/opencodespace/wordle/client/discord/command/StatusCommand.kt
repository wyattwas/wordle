package org.opencodespace.wordle.client.discord.command

import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import org.opencodespace.wordle.client.discord.utils.toChart
import org.opencodespace.wordle.client.sdk.command.Command
import org.opencodespace.wordle.client.sdk.command.NOT_REGISTERED_MESSAGE
import org.opencodespace.wordle.client.sdk.command.NO_GUESSES_FOR_TODAY_MESSAGE
import org.opencodespace.wordle.client.sdk.service.GuessService
import org.opencodespace.wordle.client.sdk.service.UserService
import org.opencodespace.wordle.client.sdk.service.WordleService
import org.opencodespace.wordle.core.filters.GuessFilter
import java.time.LocalDate

@ApplicationScoped
class StatusCommand : ListenerAdapter() {
    @Inject private lateinit var userService: UserService
    @Inject private lateinit var guessService: GuessService
    @Inject private lateinit var wordleService: WordleService

    override fun onSlashCommandInteraction(event: SlashCommandInteractionEvent) {
        if (event.name != Command.STATUS.name.lowercase()) return

        val user = userService.getUserByHandle(event.user.id)
        if (user == null) {
            event.reply(NOT_REGISTERED_MESSAGE(DiscordCommandFormatter())).setEphemeral(true).queue()
            return
        }

        val guessFilter = GuessFilter()
        guessFilter.userId = user.id
        guessFilter.date = LocalDate.now()
        val guesses = guessService.getGuesses(guessFilter)

        if (guesses.isEmpty()) {
            event.reply(NO_GUESSES_FOR_TODAY_MESSAGE(DiscordCommandFormatter())).setEphemeral(true).queue()
        }

        event.reply(guesses.toChart(wordleService.getTodaysWordle().solution)).setEphemeral(true).queue()
    }
}