plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ktor)
    application
}

group = "me.soknight.minigram"
version = "1.0"

application {
    mainClass.set("me.soknight.minigram.frontend.ApplicationKt")
}

kotlin {
    jvmToolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

tasks.run.configure {
    jvmArgs("--enable-native-access=ALL-UNNAMED")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.ktor.server.cio)
    implementation(libs.ktor.server.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.server.status.pages)
    implementation(libs.ktor.server.call.logging)
    implementation(libs.logback.classic)
}
