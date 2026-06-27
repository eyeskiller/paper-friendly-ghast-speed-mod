plugins {
    java
    id("com.gradleup.shadow") version "9.0.0-beta4"
}

group = "com.example"
version = "1.2.0"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://jitpack.io")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.1-R0.1-SNAPSHOT")
    implementation("org.bstats:bstats-bukkit:3.1.0")
}

tasks {
    withType<JavaCompile> {
        options.release.set(21)
    }
    named("build") {
        dependsOn("shadowJar")
    }
    shadowJar {
        relocate("org.bstats", "com.example.happyghast.bstats")
    }
}
