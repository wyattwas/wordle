package org.opencodespace.wordle.client.sdk.command

val NOT_REGISTERED_MESSAGE: (CommandFormatter) -> String = {
    "I'm sorry. I wasn't able to find a user in my database.\n" +
            "If you didn't register yet, run ${it.format(Command.START)}"
}

val NO_GUESSES_FOR_TODAY_MESSAGE: (CommandFormatter) -> String = {
    "Seems like you didn't guess anything yet today.\n" +
            "Try running ${it.format(Command.GUESS)}" }

val REGISTERED_FOR_THE_GAME_MESSAGE: (CommandFormatter) -> String = {
    "You have been registered for the Wordle game.\n" +
            "Your score is now being saved and can be seen by other players.\n" +
            "You can now run ${it.format(Command.GUESS)}"
}

val ALREADY_REGISTERED_MESSAGE: (CommandFormatter) -> String = {
    "You are already registered and ready to go!\n" +
            "Just run ${it.format(Command.GUESS)}"
}