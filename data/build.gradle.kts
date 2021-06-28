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
    implementation(project(":domain"))

    implementation(Dependencies.kotlin)
    implementation(Dependencies.coreKtx)

    implementation(Dependencies.Di.koin)
    implementation(Dependencies.Di.koinAndroid)

    implementation(Dependencies.Coroutines.coroutine)
    implementation(Dependencies.Coroutines.coroutineAndroid)

    implementation(Dependencies.Network.retrofit)
    implementation(Dependencies.Network.retrofitConverterGson)
    implementation(Dependencies.Network.retrofitAdapterRxJava)
    implementation(Dependencies.Network.okhttpLogInterceptor)

    debugImplementation(Dependencies.Network.chuckDebug)
    releaseImplementation(Dependencies.Network.chuckRelease)

    testImplementation(Dependencies.Tests.junit)
    androidTestImplementation(Dependencies.Tests.junits)
    androidTestImplementation(Dependencies.Tests.espressoCore)
}