import DependenciesModule.core

plugins {
    pluginAndroidLibrary()
    pluginKotlinAndroid()
    pluginKotlinAndroidExt()
}

android {
    libraryConfiguration()
}

dependencies {
    core()
}