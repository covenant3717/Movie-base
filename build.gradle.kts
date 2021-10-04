buildscript {
    repositories {
        mavenCentral()
        google()
    }
    dependencies {
        classpath(Dependencies.kotlinPlugin)
        classpath(Dependencies.gradle)
    }
}

allprojects {
    repositories {
        mavenCentral()
        google()
        maven { url = uri("https://www.jitpack.io") }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}