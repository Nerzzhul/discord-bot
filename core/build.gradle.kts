dependencies {
    implementation(project(":bot"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.flywaydb:flyway-core:9.0.4")
}

tasks.bootJar {
    enabled = false
}
