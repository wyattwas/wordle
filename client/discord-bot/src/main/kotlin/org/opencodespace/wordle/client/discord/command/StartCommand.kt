package org.opencodespace.wordle.client.discord.command

import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import org.opencodespace.wordle.client.sdk.command.ALREADY_REGISTERED_MESSAGE
import org.opencodespace.wordle.client.sdk.command.Command
import org.opencodespace.wordle.client.sdk.command.REGISTERED_FOR_THE_GAME_MESSAGE
import org.opencodespace.wordle.client.sdk.service.UserService
import org.opencodespace.wordle.core.dto.UserDTO
import org.opencodespace.wordle.core.models.Platform

@ApplicationScoped
class StartCommand : ListenerAdapter() {
    @Inject private lateinit var userService: UserService

    override fun onSlashCommandInteraction(event: SlashCommandInteractionEvent) {
        if (event.name != Command.START.name.lowercase()) return

        val user = userService.getUserByHandle(event.user.id)

        if (user == null) {
            val newUser = UserDTO(
                name = event.user.name,
                handle = event.user.id,
                platform = Platform.Discord,
                score = 0
            )

            userService.createUser(newUser)

            event.reply(REGISTERED_FOR_THE_GAME_MESSAGE(DiscordCommandFormatter())).setEphemeral(true).queue()
            return
        }

        event.reply(ALREADY_REGISTERED_MESSAGE(DiscordCommandFormatter())).setEphemeral(true).queue()
    }
}