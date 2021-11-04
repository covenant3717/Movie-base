import DependenciesModule.app

plugins {
    pluginAndroidApplication()
    pluginKotlinAndroid()
    pluginKotlinAndroidExt()
    pluginKotlinKapt()
    pluginNavigationSafeArgs()
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