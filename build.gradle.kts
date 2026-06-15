plugins {
    java
    id("com.gradleup.shadow") version "8.3.5"
}

group = "com.example"
version = "1.1.0"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://jitpack.io")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.1-R0.1-SNAPSHOT")
    implementation("com.github.eyeskiller:plugin-analytics-api:v1.1.1")
}

tasks {
    withType<JavaCompile> {
        options.release.set(21)
    }
    named("build") {
        dependsOn("shadowJar")
    }
}
