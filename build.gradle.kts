import groovy.xml.dom.DOMCategory.attributes

plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "org.example.Main"
    }

    // Include dependencies in the jar (fat jar)
    from({
        configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
    })
}

tasks.register("stage") {
    dependsOn("build")
}


tasks.test {
    useJUnitPlatform()
}

