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
    const val retrofit = "2.9.0"
    const val retrofitAdapterCoroutinesVersion = "1.0.0"
    const val okhttpLogInterceptor = "4.9.0"
    const val chuck = "1.1.0"
    const val rxJava = "3.0.11"
    const val rxAndroid = "3.0.0"
    const val rxKotlin = "3.0.1"

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

    object RX {
        const val rxJava = "io.reactivex.rxjava3:rxjava:${Versions.rxJava}"
        const val rxAndroid = "io.reactivex.rxjava3:rxandroid:${Versions.rxAndroid}"
        const val rxKotlin = "io.reactivex.rxjava3:rxkotlin:${Versions.rxKotlin}"
    }

    object Di {
        const val koin = "org.koin:koin-core:${Versions.koin}"
        const val koinAndroid = "org.koin:koin-android:${Versions.koin}"
        const val koinViewModel = "org.koin:koin-android-viewmodel:${Versions.koin}"
        const val koinTest = "org.koin:koin-test:${Versions.koin}"
    }

    object Network {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val retrofitConverterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
        const val retrofitAdapterRxJava = "com.squareup.retrofit2:adapter-rxjava3:${Versions.retrofit}"
        const val retrofitAdapterCoroutines = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-experimental-adapter:${Versions.retrofitAdapterCoroutinesVersion}"
        const val okhttpLogInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttpLogInterceptor}"
        const val chuckDebug = "com.readystatesoftware.chuck:library:${Versions.chuck}"
        const val chuckRelease = "com.readystatesoftware.chuck:library-no-op:${Versions.chuck}"
    }

    object Tests {
        const val junit = "junit:junit:${Versions.junit}"
        const val junits = "androidx.test.ext:junit:${Versions.junitTest}"
        const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
    }
}