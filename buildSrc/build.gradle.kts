plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
    groovy
}

repositories {
    maven {
        name = "Gradle libs"
        url = uri("https://repo.gradle.org/gradle/libs")
    }
}

dependencies {
    testImplementation("org.spockframework:spock-core:1.3-groovy-2.5")
}
