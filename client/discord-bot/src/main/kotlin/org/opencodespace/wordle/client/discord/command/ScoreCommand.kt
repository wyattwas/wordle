package org.opencodespace.wordle.client.discord.command

import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import org.opencodespace.wordle.client.sdk.command.Command
import org.opencodespace.wordle.client.sdk.service.UserService
import org.opencodespace.wordle.core.models.Platform

@ApplicationScoped
class ScoreCommand : ListenerAdapter() {
    @Inject private lateinit var userService: UserService

    override fun onSlashCommandInteraction(event: SlashCommandInteractionEvent) {
        if (event.name != Command.SCORE.name.lowercase()) return

        val users = userService.getUsers().sortedByDescending { it.score }

        val topThree = users.take(3)
        val currentUser = users.find { it.handle == event.user.id }

        val lines = mutableListOf("🏆 Top 3 Players:")

        topThree.forEachIndexed { index, user ->
            val userName = if (user.platform == Platform.Discord) {
                event.jda.retrieveUserById(user.handle).complete().asMention
            } else {
                "${user.name} (${user.handle} - ${user.platform})"
            }
            lines.add("${index + 1}. $userName - ${user.score} points")
        }

        if (currentUser != null && currentUser !in topThree) {
            lines.add("")
            lines.add("Your position:")

            val userName = if (currentUser.platform == Platform.Discord) {
                event.jda.retrieveUserById(currentUser.handle!!).complete().asMention
            } else {
                "${currentUser.name} (${currentUser.handle} - ${currentUser.platform})"
            }

            val rank = users.indexOf(currentUser) + 1
            lines.add("$rank. $userName - ${currentUser.score} points")
        }

        val body = lines.joinToString("\n")

        event.reply(body).queue()
    }
}