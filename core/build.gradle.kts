import DependenciesModule.core

plugins {
    pluginAndroidLibrary()
    pluginKotlinAndroid()
    pluginKotlinAndroidExt()
}

android {
    compileSdk = Versions.App.COMPILE_SDK
    defaultConfig {
        minSdk = Versions.App.MIN_SDK
    }
}

dependencies {
    core()
}