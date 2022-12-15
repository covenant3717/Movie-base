import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.Action
import org.gradle.internal.impldep.com.amazonaws.PredefinedClientConfigurations.defaultConfig

fun BaseAppModuleExtension.appConfiguration() {
    compileSdk = Versions.App.COMPILE_SDK
    defaultConfig {
        applicationId = Versions.App.APPLICATION_ID
        minSdk = Versions.App.MIN_SDK
        targetSdk = Versions.App.TARGET_SDK
        versionCode = Versions.App.VERSION_CODE
        versionName = Versions.App.VERSION_NAME
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
            isZipAlignEnabled = true
            isJniDebuggable = false
            isRenderscriptDebuggable = false
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

fun BaseExtension.libraryConfiguration() {
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
