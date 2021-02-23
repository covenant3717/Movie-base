plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(Apps.compileSdk)
    defaultConfig {
        applicationId(Apps.appId)
        minSdkVersion(Apps.minSdk)
        targetSdkVersion(Apps.targetSdk)
        versionCode = Apps.versionCode
        versionName = Apps.versionName
        testInstrumentationRunner("androidx.test.runner.AndroidJUnitRunner")
        vectorDrawables.useSupportLibrary = true
    }
    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            isDebuggable = true
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-dev"
        }
        getByName("release") {
            isMinifyEnabled = true
            isDebuggable = false
            isShrinkResources = true
            isZipAlignEnabled = true
            isJniDebuggable = false
            isRenderscriptDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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
    implementation(project(":data"))

    implementation(Libs.kotlin)
    implementation(Libs.coreKtx)

    implementation(Libs.Jetpack.viewmodel)
    implementation(Libs.Jetpack.livedata)
    implementation(Libs.Jetpack.runtime)

    implementation(Libs.UI.appcompat)
    implementation(Libs.UI.material)
    implementation(Libs.UI.constraintlayout)

    implementation(Libs.Coroutines.coroutine)
    implementation(Libs.Coroutines.coroutineAndroid)

    implementation(Libs.Di.koin)
    implementation(Libs.Di.koinAndroid)

    testImplementation(Libs.Tests.junit)
    androidTestImplementation(Libs.Tests.junits)
    androidTestImplementation(Libs.Tests.espressoCore)
}