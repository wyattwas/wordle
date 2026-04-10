package org.opencodespace.wordle.client.discord

import io.quarkus.runtime.Quarkus
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        Quarkus.run(BotApplication::class.java, *args)
    }
}