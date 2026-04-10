package org.opencodespace.wordle.client.discord

import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import net.dv8tion.jda.api.OnlineStatus
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.requests.GatewayIntent
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder
import net.dv8tion.jda.api.sharding.ShardManager
import org.opencodespace.wordle.client.discord.command.CommandRegister
import org.opencodespace.wordle.client.discord.command.GuessCommand
import org.opencodespace.wordle.client.discord.command.ScoreCommand
import org.opencodespace.wordle.client.discord.command.StartCommand
import org.opencodespace.wordle.client.discord.command.StatusCommand
import org.opencodespace.wordle.client.discord.command.UserCommand

@ApplicationScoped
class DiscordBot {
    lateinit var shardManager: ShardManager

    @Inject
    private lateinit var userCommand: UserCommand
    @Inject
    private lateinit var guessCommand: GuessCommand
    @Inject
    private lateinit var scoreCommand: ScoreCommand
    @Inject
    private lateinit var startCommand: StartCommand
    @Inject
    private lateinit var statusCommand: StatusCommand

    fun start() {
        val builder = DefaultShardManagerBuilder.createDefault("")
        builder.setStatus(OnlineStatus.ONLINE)
        builder.setActivity(Activity.playing("Playing a game of Wordle"))
        builder.enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_MESSAGES)
        builder.addEventListeners(
            CommandRegister(),
            userCommand,
            guessCommand,
            scoreCommand,
            startCommand,
            statusCommand
        )

        shardManager = builder.build()
    }
}