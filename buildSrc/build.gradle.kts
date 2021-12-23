plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    google()
}

dependencies {
    // This should be in-sync with the "GRADLE" version exposed by "Versions"
    implementation("com.android.tools.build:gradle:7.0.4")

    // This should be in-sync with the "KOTLIN" version exposed by "Versions"
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.31")

    implementation(kotlin("script-runtime"))
}