import DependenciesModule.featureActor

plugins {
    pluginAndroidLibrary()
    pluginKotlinAndroid()
    pluginKotlinAndroidExt()
}

android {
    libraryConfiguration()
}

dependencies {
    featureActor()
}