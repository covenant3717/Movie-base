import DependenciesModule.data

plugins {
    pluginAndroidLibrary()
    pluginKotlinAndroid()
    pluginKotlinAndroidExt()
    pluginKotlinKapt()
}

android {
    compileSdk = Versions.App.COMPILE_SDK
    buildTypes {
        getByName("debug") {
            buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org\"")
            buildConfigField("String", "API_KEY", "\"791455e526b737816137912ea56a5ba4\"")
            buildConfigField("String", "API_VERSION", "\"3\"")
        }
        getByName("release") {
            buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org\"")
            buildConfigField("String", "API_KEY", "\"791455e526b737816137912ea56a5ba4\"")
            buildConfigField("String", "API_VERSION", "\"3\"")
        }
    }
}

dependencies {
    data()
}