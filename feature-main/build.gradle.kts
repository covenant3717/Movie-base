import DependenciesModule.featureMain

plugins {
    pluginAndroidLibrary()
    pluginKotlinAndroid()
    pluginKotlinAndroidExt()
}

android {
    libraryConfiguration()
}

dependencies {
    featureMain()
}