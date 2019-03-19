plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    maven {
        name = "Gradle libs"
        url = uri("https://repo.gradle.org/gradle/libs")
    }
}
