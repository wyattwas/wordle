plugins {
    kotlin("jvm") version "2.1.21" apply false
}

allprojects {
    group = "org.opencodespace"
    version = "1.0.0"

    repositories {
        mavenCentral()
        mavenLocal()
    }
}