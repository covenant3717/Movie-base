import DependenciesModule.data

plugins {
    pluginAndroidLibrary()
    pluginKotlinAndroid()
    pluginKotlinAndroidExt()
    pluginKotlinKapt()
}

android {
    dataConfiguration()
}

dependencies {
    data()
}