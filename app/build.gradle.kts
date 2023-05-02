import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "org.geepawhill"
version = "1.0-SNAPSHOT"

plugins {
    application
    kotlin("jvm") version "1.8.21"
    id("org.openjfx.javafxplugin") version "0.0.13"
}

repositories {
    maven {
        setUrl("https://oss.sonatype.org/content/repositories/snapshots/")
    }
    mavenCentral()
}

application {
    mainClass.set("org.geepawhill.kontentment.app.AppKt")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

javafx {
    version = "17"
    modules = mutableListOf(
        "javafx.controls",
        "javafx.graphics",
        "javafx.fxml",
        "javafx.web"
    )
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("no.tornado:tornadofx:2.0.0-SNAPSHOT")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
    testImplementation( "org.assertj:assertj-core:3.23.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")
}