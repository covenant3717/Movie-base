plugins {
    appPlugins()
}

android {
    appConfiguration()
    kotlinOptions {
        jvmTarget = Versions.Main.JAVA.toString()
    }
}

dependencies {
    app()
}