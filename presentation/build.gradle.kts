plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(Versions.App.COMPILE_SDK)
    defaultConfig {
        applicationId(Versions.App.APPLICATION_ID)
        minSdkVersion(Versions.App.MIN_SDK)
        targetSdkVersion(Versions.App.TARGET_SDK)
        versionCode = Versions.App.VERSION_CODE
        versionName = Versions.App.VERSION_NAME
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
        sourceCompatibility = Versions.JAVA
        targetCompatibility = Versions.JAVA
    }
    kotlinOptions {
        jvmTarget = Versions.JAVA.toString()
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":data"))

    implementation(Libs.kotlin)
    implementation(Libs.coreKtx)
    implementation(Libs.coil)

    implementation(Libs.Jetpack.viewmodel)
    implementation(Libs.Jetpack.livedata)
    implementation(Libs.Jetpack.runtime)

    implementation(Libs.UI.appcompat)
    implementation(Libs.UI.material)
    implementation(Libs.UI.constraintLayout)
    implementation(Libs.UI.groupie)
    implementation(Libs.UI.groupieExt)
    implementation(Libs.UI.circleImageView)

    implementation(Libs.Di.koin)
    implementation(Libs.Di.koinAndroid)
    implementation(Libs.Di.koinViewModel)

    implementation(Libs.Coroutines.coroutine)
    implementation(Libs.Coroutines.coroutineAndroid)

    testImplementation(Libs.Tests.junit)
    androidTestImplementation(Libs.Tests.junits)
    androidTestImplementation(Libs.Tests.espressoCore)
}