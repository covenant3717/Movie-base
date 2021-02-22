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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(Libs.kotlin)
    implementation(Libs.coreKtx)

    implementation(Libs.Di.koin)
    implementation(Libs.Di.koinAndroid)

    implementation(Libs.Network.retrofit)
    implementation(Libs.Network.retrofitConverterGson)
    implementation(Libs.Network.retrofitAdapterRxJava)
    implementation(Libs.Network.stetho)
    implementation(Libs.Network.stethoOkhttp)

    testImplementation(Libs.Tests.junit)
    androidTestImplementation(Libs.Tests.junits)
    androidTestImplementation(Libs.Tests.espressoCore)
}