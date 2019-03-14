package com.gradle.introduction.build

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.SourceSetContainer
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.the

class TestConfigurationPlugin : Plugin<Project> {

    override fun apply(project: Project): Unit = project.run {
        val container = container(TestConfiguration::class.java)
        extensions.add("testConfigurations", container)

        container.all {
            register(this)
        }
    }

     private fun Project.register(testConfiguration: TestConfiguration) {
         val sourceSets = the<SourceSetContainer>()

         val sourceSet = sourceSets.create(testConfiguration.taskName) {
             java.srcDir(file(testConfiguration.javaSrcDir))
             resources.srcDir(file(testConfiguration.resourcesSrcDir))
             compileClasspath += sourceSets["main"].output
             runtimeClasspath += sourceSets["main"].output
         }

         configurations["integrationTestImplementation"].extendsFrom(configurations["testImplementation"])
         configurations["integrationTestRuntimeOnly"].extendsFrom(configurations["testRuntimeOnly"])

         val integrationTest = tasks.register(testConfiguration.taskName, Test::class.java) {
             description = testConfiguration.taskDescription
             group = "verification"

             testClassesDirs = sourceSet.output.classesDirs
             classpath = sourceSet.runtimeClasspath
             mustRunAfter(tasks.named("test"))
         }

         tasks.named("check") {
             dependsOn(integrationTest)
         }
    }
}
