package com.gradle.introduction.build

import spock.lang.Specification

class TestConfigurationTest extends Specification {

    def conf = new TestConfiguration("integration")

    def 'should create task name'() {
        expect:
        conf.taskName == "integrationTest"
    }

    def 'should create task description'() {
        expect:
        conf.taskDescription == "Runs the integration tests."
    }

    def 'should create java src dir'() {
        expect:
        conf.javaSrcDir == "src/integration-test/java"
    }

    def 'should create resources src dir'() {
        expect:
        conf.resourcesSrcDir == "src/integration-test/resources"
    }
}
