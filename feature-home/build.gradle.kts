import DependenciesModule.featureHome

plugins {
    pluginAndroidLibrary()
    pluginKotlinAndroid()
    pluginKotlinAndroidExt()
}

android {
    libraryConfiguration()
}

dependencies {
    featureHome()
}