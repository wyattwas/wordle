package org.opencodespace.wordle.client.sdk.command

interface CommandFormatter {
    fun format(command: Command): String
}