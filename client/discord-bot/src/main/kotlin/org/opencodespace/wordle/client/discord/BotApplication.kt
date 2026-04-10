package org.opencodespace.wordle.client.discord

import io.quarkus.runtime.QuarkusApplication
import jakarta.inject.Inject
import javax.security.auth.login.LoginException

class BotApplication : QuarkusApplication {
    @Inject private lateinit var discordBot: DiscordBot

    @Throws(Exception::class)
    override fun run(vararg args: String): Int {
        try {
            discordBot.start()
        } catch (e: LoginException) {
            e.printStackTrace()
        }

        Thread.currentThread().join()
        return 0
    }
}