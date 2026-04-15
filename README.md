# Wordle project

I love playing the Wordle game.
The most convent way to share my results and stats with my friends is by using the Discord Wordle bot.
However, I am trying to move away from Discord more and more. Since I knew I couldn't get all my friends to switch with
me to another platform right away, I needed a solution that would work on multiple platforms.
That's what this project is for.

The project contains a backend server - Quarkus RESTful API<br>
a common code base - the core module<br>
and clients, currently for Discord and Matrix - Discord JDA with Quarkus REST client and Matrix Trixnity with Ktor

Gradle Modules:
- core
- server
- client:sdk (probably going to remove this, since the matrix bot is probably going to use Ktor instead of Quarkus REST client)
- client:discord-bot
- client:matrix-bot