plugins {
    java
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("org.springframework.boot:spring-boot-dependencies:2.1.3.RELEASE"))
    implementation("com.google.guava:guava:27.0.1-jre")
    implementation("org.springframework.boot:spring-boot-starter-web")

    testImplementation("junit:junit:4.12")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.register<Zip>("zipSources") {
    archiveClassifier.set("src")
    from(sourceSets.main.get().allJava)
}
