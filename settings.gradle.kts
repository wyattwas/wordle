rootProject.name = "wordle"

include("core")
include("server")
include("client:discord-bot")
include("client:matrix-bot")

pluginManagement {
    val quarkusPluginVersion: String by settings
    val quarkusPluginId: String by settings
    repositories {
        mavenCentral()
        gradlePluginPortal()
        mavenLocal()
    }
    plugins {
        id(quarkusPluginId) version quarkusPluginVersion
        id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
    }
}