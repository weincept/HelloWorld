plugins {
    id("org.jetbrains.kotlin.jvm") version "1.9.23"
    id("application")
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass.set("org.example.Main") // Or use full package if applicable
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "Main"
    }
}

tasks.register("stage") {
    dependsOn("shadowJar")
}
