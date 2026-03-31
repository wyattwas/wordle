plugins {
    kotlin("jvm") version "2.1.21"
}

group = "org.opencodespace.wordle.client"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(23)
}