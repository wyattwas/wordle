package org.opencodespace.wordle.client.discord.command

import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import org.opencodespace.wordle.client.sdk.command.Command
import org.opencodespace.wordle.client.sdk.service.UserService
import org.opencodespace.wordle.core.filters.UserFilter
import org.opencodespace.wordle.core.models.Platform
import java.util.*

@ApplicationScoped
class UserCommand : ListenerAdapter() {
    @Inject private lateinit var userService: UserService

    override fun onSlashCommandInteraction(event: SlashCommandInteractionEvent) {
        if (event.name != Command.USER.name.lowercase()) return
        val name = requireNotNull(event.getOption("name")).asString

        val filter = UserFilter()
        filter.name = name
        val users = userService.getUsers(filter)

        val titleEmbed = EmbedBuilder()
        titleEmbed.setTitle("Users")
            .setDescription("A List of all users")

        val callback = event.replyEmbeds(titleEmbed.build())

        for (user in users) {
            val embed = EmbedBuilder()

            embed.setTitle(user.name)
                .setDescription(
                    if (user.platform == Platform.Discord)
                        event.jda.retrieveUserById(user.handle).complete()
                            .asMention
                    else
                        user.handle
                )
                .addField("Score", user.score.toString(), true)
                .addField("Platform", user.platform.toString(), true)

            callback.addEmbeds(embed.build())
        }

        callback.queue()
    }
}
