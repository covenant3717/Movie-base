plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    google()
}

dependencies {
    // This should be in-sync with the "GRADLE" version exposed by "Versions"
    implementation("com.android.tools.build:gradle:7.2.1")

    // This should be in-sync with the "KOTLIN" version exposed by "Versions"
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.0")

    implementation(kotlin("script-runtime"))
}