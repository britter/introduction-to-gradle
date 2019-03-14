package com.gradle.introduction.build

class TestConfiguration(val name: String) {

    val taskName = "${name}Test"

    val taskDescription = "Runs the $name tests."

    val javaSrcDir = "src/$name-test/java"

    val resourcesSrcDir = "src/$name-test/resources"
}
