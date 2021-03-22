plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(Apps.compileSdk)
    defaultConfig {
        minSdkVersion(Apps.minSdk)
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":domain"))

    implementation(Libs.kotlin)
    implementation(Libs.coreKtx)

    implementation(Libs.Di.koin)
    implementation(Libs.Di.koinAndroid)

    implementation(Libs.RX.rxJava)
    implementation(Libs.RX.rxAndroid)
    implementation(Libs.RX.rxKotlin)

    implementation(Libs.Network.retrofit)
    implementation(Libs.Network.retrofitConverterGson)
    implementation(Libs.Network.retrofitAdapterRxJava)
    implementation(Libs.Network.okhttpLogInterceptor)

    debugImplementation(Libs.Network.chuckDebug)
    releaseImplementation(Libs.Network.chuckRelease)

    testImplementation(Libs.Tests.junit)
    androidTestImplementation(Libs.Tests.junits)
    androidTestImplementation(Libs.Tests.espressoCore)
}