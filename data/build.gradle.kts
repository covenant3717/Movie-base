import DependenciesModule.data

plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.KOTLIN_ANDROID_EXTENSIONS)
    id(Plugins.KOTLIN_KAPT)
}

android {
    compileSdkVersion(Versions.App.COMPILE_SDK)
    defaultConfig {
        minSdkVersion(Versions.App.MIN_SDK)
        testInstrumentationRunner("androidx.test.runner.AndroidJUnitRunner")
        consumerProguardFiles("consumer-rules.pro")
    }
    buildTypes {
        getByName("debug") {
            buildConfigField("String", "BASE_URL", "\"https://api.spoonacular.com\"")
            buildConfigField("String", "API_KEY", "\"0c548a1cd7174a8998401265afa99400\"")
        }
        getByName("release") {
            buildConfigField("String", "BASE_URL", "\"https://api.spoonacular.com\"")
            buildConfigField("String", "API_KEY", "\"0c548a1cd7174a8998401265afa99400\"")
        }
    }
    compileOptions {
        sourceCompatibility = Versions.JAVA
        targetCompatibility = Versions.JAVA
    }
    kotlinOptions {
        jvmTarget = Versions.JAVA.toString()
    }
}

dependencies {
    data()
}