buildscript {
    repositories {
        mavenCentral()
        google()
    }
    dependencies {
        classpath(Dependencies.Main.kotlinPlugin)
        classpath(Dependencies.Main.gradle)
        classpath(Dependencies.Main.navigationSafeArgPlugin)
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