buildscript {
    repositories {
        mavenCentral()
    }
}

val springVersion = "2.7.1"

plugins {
    val springBootVersion = "2.7.1"
    id("io.spring.dependency-management") version "1.0.11.RELEASE" apply false
    id("org.springframework.boot") version springBootVersion apply false
    java
}

allprojects {
    group = "com.ebyte"
    version = "1.0-SNAPSHOT"

    repositories {
        mavenCentral()
    }
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

subprojects {
    apply {
        plugin("io.spring.dependency-management")
        plugin("org.springframework.boot")
        plugin("java")
    }
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}