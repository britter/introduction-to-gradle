plugins {
    java
    id("com.gradle.introduction.build.TestConfigurationPlugin")
}

repositories {
    mavenCentral()
}

testConfigurations {
    create("integration")
    create("ui")
}

val integrationTestImplementation by configurations.getting

dependencies {
    implementation(platform("org.springframework.boot:spring-boot-dependencies:2.1.3.RELEASE"))
    implementation("com.google.guava:guava:27.0.1-jre")
    implementation("org.springframework.boot:spring-boot-starter-web")

    testImplementation("junit:junit:4.12")

    integrationTestImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.register<Zip>("zipSources") {
    archiveClassifier.set("src")
    from(sourceSets.main.get().allJava)
}
