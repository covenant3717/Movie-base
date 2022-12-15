import com.android.build.gradle.BaseExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension

fun BaseAppModuleExtension.appConfiguration() {
    defaultConfig {
        applicationId = Versions.App.APPLICATION_ID
        compileSdk = Versions.App.COMPILE_SDK
        minSdk = Versions.App.MIN_SDK
        targetSdk = Versions.App.TARGET_SDK
        versionCode = Versions.App.VERSION_CODE
        versionName = Versions.App.VERSION_NAME
        vectorDrawables.useSupportLibrary = true
    }
    buildTypes {
        getByName("debug") {
            buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org\"")
            buildConfigField("String", "API_KEY", "\"791455e526b737816137912ea56a5ba4\"")
            buildConfigField("String", "API_VERSION", "\"3\"")
            isMinifyEnabled = false
            isDebuggable = true
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-dev"
        }
        getByName("release") {
            buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org\"")
            buildConfigField("String", "API_KEY", "\"791455e526b737816137912ea56a5ba4\"")
            buildConfigField("String", "API_VERSION", "\"3\"")
            isMinifyEnabled = true
            isDebuggable = false
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = Versions.Main.JAVA
        targetCompatibility = Versions.Main.JAVA
    }
    kotlinOptions {
        jvmTarget = Versions.Main.JAVA.toString()
    }
    buildFeatures.compose = true
    composeOptions.kotlinCompilerExtensionVersion = Versions.Main.KOTLIN_COMPILER_EXTENSION
}

fun BaseExtension.coreConfiguration() {
    compileSdkVersion(Versions.App.COMPILE_SDK)
    defaultConfig {
        minSdk = Versions.App.MIN_SDK
    }
    buildFeatures.compose = true
    composeOptions.kotlinCompilerExtensionVersion = Versions.Main.KOTLIN_COMPILER_EXTENSION
}

fun BaseExtension.dataConfiguration() {
    compileSdkVersion(Versions.App.COMPILE_SDK)
    defaultConfig {
        minSdk = Versions.App.MIN_SDK
    }
}

fun BaseExtension.featureConfiguration() {
    compileSdkVersion(Versions.App.COMPILE_SDK)
    defaultConfig {
        minSdk = Versions.App.MIN_SDK
    }
    buildFeatures.compose = true
    composeOptions.kotlinCompilerExtensionVersion = Versions.Main.KOTLIN_COMPILER_EXTENSION
}

