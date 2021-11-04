import com.android.build.gradle.BaseExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension

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
        sourceCompatibility = Versions.Main.JAVA
        targetCompatibility = Versions.Main.JAVA
    }
}

fun BaseExtension.dataConfiguration() {
    libraryConfiguration()
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

fun BaseExtension.libraryConfiguration() {
    compileSdkVersion(Versions.App.COMPILE_SDK)
    defaultConfig {
        minSdk = Versions.App.MIN_SDK
    }
}
