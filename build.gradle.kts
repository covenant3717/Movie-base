buildscript {
    repositories {
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
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}