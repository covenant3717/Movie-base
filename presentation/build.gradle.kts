plugins {
    id(Plugins.ANDROID_APPLICATION)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.KOTLIN_ANDROID_EXTENSIONS)
    id(Plugins.KOTLIN_KAPT)
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

    implementation(Dependencies.kotlin)
    implementation(Dependencies.coreKtx)
    implementation(Dependencies.coil)

    implementation(Dependencies.Jetpack.viewmodel)
    implementation(Dependencies.Jetpack.livedata)
    implementation(Dependencies.Jetpack.runtime)

    implementation(Dependencies.UI.appcompat)
    implementation(Dependencies.UI.material)
    implementation(Dependencies.UI.constraintLayout)
    implementation(Dependencies.UI.groupie)
    implementation(Dependencies.UI.groupieExt)
    implementation(Dependencies.UI.circleImageView)

    implementation(Dependencies.Di.koin)
    implementation(Dependencies.Di.koinAndroid)
    implementation(Dependencies.Di.koinViewModel)

    implementation(Dependencies.Coroutines.coroutine)
    implementation(Dependencies.Coroutines.coroutineAndroid)

    testImplementation(Dependencies.Tests.junit)
    androidTestImplementation(Dependencies.Tests.junits)
    androidTestImplementation(Dependencies.Tests.espressoCore)
}