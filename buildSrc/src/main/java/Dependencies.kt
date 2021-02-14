object Apps {
    const val compileSdk = 30
    const val appId = "com.evgenykuksov.recipes"
    const val minSdk = 21
    const val targetSdk = 30
    const val versionCode = 1
    const val versionName = "1.0"
}

object Versions {
    const val kotlin = "1.4.21"
    const val gradle = "4.1.1"
    const val coreKtx = "1.3.2"
    const val lifecycle = "2.2.0"
    const val coroutine = "1.4.2"
    const val koin = "2.2.2"

    const val appCompat = "1.2.0"
    const val material = "1.2.1"
    const val constraintLayout = "2.0.4"

    const val junit = "4.12"
    const val junitTest = "1.1.2"
    const val espressoCore = "3.3.0"
}

object Libs {
    const val kotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val gradle = "com.android.tools.build:gradle:${Versions.gradle}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"

    object Jetpack {
        const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
        const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
        const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    }

    object UI {
        const val appcompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
        const val material = "com.google.android.material:material:${Versions.material}"
        const val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    }

    object Coroutines {
        const val coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutine}"
        const val coroutineAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutine}"
    }

    object Di {
        const val koin = "org.koin:koin-core:${Versions.koin}"
        const val koinAndroid = "org.koin:koin-android:${Versions.koin}"
        const val koinTest = "org.koin:koin-test:${Versions.koin}"
    }

    object Tests {
        const val junit = "junit:junit:${Versions.junit}"
        const val junits = "androidx.test.ext:junit:${Versions.junitTest}"
        const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
    }
}