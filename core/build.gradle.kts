dependencies {
    implementation(project(":bot"))
    implementation("org.springframework.boot:spring-boot-starter-web")
}

tasks.bootJar {
    enabled = false
}
