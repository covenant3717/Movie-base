buildscript {
    repositories {
        mavenCentral()
        google()
        jcenter()
    }
    dependencies {
        classpath(Libs.kotlinPlugin)
        classpath(Libs.gradle)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven { url = uri("https://www.jitpack.io") }
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}