import DependenciesModule.featureMovie

plugins {
    pluginAndroidLibrary()
    pluginKotlinAndroid()
    pluginKotlinAndroidExt()
}

android {
    libraryConfiguration()
}

dependencies {
    featureMovie()
}