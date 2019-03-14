plugins {
    java
}

repositories {
    mavenCentral()
}

sourceSets {
    create("integrationTest") {
        java.srcDir(file("src/integration-test/java"))
        resources.srcDir(file("src/integration-test/resources"))
        compileClasspath += sourceSets["main"].output
        runtimeClasspath += sourceSets["main"].output
    }
}

val integrationTestImplementation by configurations.getting {
    extendsFrom(configurations["testImplementation"])
}

configurations["integrationTestRuntimeOnly"].extendsFrom(configurations["testRuntimeOnly"])

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

val integrationTest = tasks.register<Test>("integrationTest") {
    description = "Runs the integration tests."
    group = "verification"

    testClassesDirs = sourceSets["integrationTest"].output.classesDirs
    classpath = sourceSets["integrationTest"].runtimeClasspath
    mustRunAfter(tasks["test"])
}

tasks.check {
    dependsOn(integrationTest)
}
