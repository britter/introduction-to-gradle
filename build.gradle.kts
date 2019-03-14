plugins {
    java
    application
}

repositories {
    jcenter()
}

dependencies {
    implementation("com.google.guava:guava:27.0.1-jre")

    testImplementation("junit:junit:4.12")
}

application {
    mainClassName = "com.gradle.simple.App"
}

tasks.register<Zip>("zipSources") {
    archiveClassifier.set("src")
    from(sourceSets.main.get().allJava)
}
