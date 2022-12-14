buildscript {
    repositories {
        mavenCentral()
    }
}

plugins {
    val springBootVersion = "2.7.2"
    id("io.spring.dependency-management") version "1.0.11.RELEASE" apply false
    id("org.springframework.boot") version springBootVersion apply false
    id("io.freefair.lombok") version "6.5.0.3" apply false
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
        plugin("io.freefair.lombok")
    }
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}