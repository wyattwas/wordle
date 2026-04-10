package org.opencodespace.wordle.client.discord.command

import org.opencodespace.wordle.client.sdk.command.Command
import org.opencodespace.wordle.client.sdk.command.CommandFormatter

class DiscordCommandFormatter : CommandFormatter {
    private val commandIds = mapOf(
        Command.GUESS to "1487509975684223168",
        Command.START to "1488234589980852224",
        Command.SCORE to "1487601114861015181",
        Command.STATUS to "1488248776866009241",
        Command.USER to "1487482954446802954"
    )

    override fun format(command: Command): String {
        val id = commandIds[command]
        return "</${command.name.lowercase()}:$id>"
    }
}