package org.opencodespace.wordle.client.discord.command

import net.dv8tion.jda.api.events.guild.GuildJoinEvent
import net.dv8tion.jda.api.events.guild.GuildReadyEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.interactions.commands.OptionType
import net.dv8tion.jda.api.interactions.commands.build.CommandData
import net.dv8tion.jda.api.interactions.commands.build.Commands
import org.opencodespace.wordle.client.sdk.command.Command as CommandsEnum

class CommandRegister : ListenerAdapter() {
    private val commands: List<CommandData> = object : ArrayList<CommandData>() {
        init {
            add(
                Commands.slash(CommandsEnum.USER.name.lowercase(), "Display users by name")
                    .addOption(OptionType.STRING, "name", "name to filter by", true)
            )
            add(
                Commands.slash(CommandsEnum.GUESS.name.lowercase(), "Try guessing today's Wordle")
                    .addOption(OptionType.STRING, "guess", "Your guess", true)
            )
            add(Commands.slash(CommandsEnum.SCORE.name.lowercase(), "Show the scoreboard"))
            add(Commands.slash(CommandsEnum.START.name.lowercase(), "Get started with the Wordle bot"))
            add(Commands.slash(CommandsEnum.STATUS.name.lowercase(), "See how far you got today"))
        }
    }

    override fun onGuildReady(event: GuildReadyEvent) {
        event.getGuild().updateCommands().addCommands(commands).queue()
    }

    override fun onGuildJoin(event: GuildJoinEvent) {
        event.getGuild().updateCommands().addCommands(commands).queue()
    }
}
