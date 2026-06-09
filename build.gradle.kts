plugins {
    java
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:26.1.2-R0.1-SNAPSHOT")
}

tasks {
    withType<JavaCompile> {
        options.release.set(21)
    }
}
