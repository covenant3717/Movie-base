import DependenciesModule.core

plugins {
    pluginAndroidLibrary()
    pluginKotlinAndroid()
}

android {
    compileSdk = Versions.App.COMPILE_SDK
}

dependencies {
    core()
}