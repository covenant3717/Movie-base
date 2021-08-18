object Dependencies {
    const val kotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.Main.KOTLIN}"
    const val gradle = "com.android.tools.build:gradle:${Versions.Main.GRADLE}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.Main.KOTLIN}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.Main.CORE_KTX}"
    const val coil = "io.coil-kt:coil:${Versions.Rest.COIL}"
    const val coil_gif = "io.coil-kt:coil-gif:${Versions.Rest.COIL_GIF}"

    object Jetpack {
        const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.Rest.LIFECYCLE}"
        const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.Rest.LIFECYCLE}"
        const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.Rest.LIFECYCLE}"
    }

    object UI {
        const val appcompat = "androidx.appcompat:appcompat:${Versions.UI.APP_COMPAT}"
        const val material = "com.google.android.material:material:${Versions.UI.MATERIAL}"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.UI.CONSTRAINT_LAYOUT}"
        const val fragment = "androidx.fragment:fragment:${Versions.UI.FRAGMENT}"
        const val fragmentKts = "androidx.fragment:fragment-ktx:${Versions.UI.FRAGMENT}"
        const val groupie = "com.github.lisawray.groupie:groupie:${Versions.UI.GROUPIE}"
        const val groupieExt = "com.github.lisawray.groupie:groupie-kotlin-android-extensions:${Versions.UI.GROUPIE}"
        const val circleImageView = "de.hdodenhof:circleimageview:${Versions.UI.CIRCLE_IMAGE_VIEW}"
        const val shimmer = "com.facebook.shimmer:shimmer:${Versions.UI.SHIMMER}"
    }

    object Coroutines {
        const val coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Threads.COROUTINE}"
        const val coroutineAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Threads.COROUTINE}"
    }

    object RX {
        const val rxJava = "io.reactivex.rxjava3:rxjava:${Versions.Threads.RX_JAVA}"
        const val rxAndroid = "io.reactivex.rxjava3:rxandroid:${Versions.Threads.RX_ANDROID}"
        const val rxKotlin = "io.reactivex.rxjava3:rxkotlin:${Versions.Threads.RX_KOTLIN}"
    }

    object Di {
        const val koin = "org.koin:koin-core:${Versions.Rest.KOIN}"
        const val koinAndroid = "org.koin:koin-android:${Versions.Rest.KOIN}"
        const val koinViewModel = "org.koin:koin-android-viewmodel:${Versions.Rest.KOIN}"
        const val koinTest = "org.koin:koin-test:${Versions.Rest.KOIN}"
    }

    object Network {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.Network.RETROFIT}"
        const val retrofitConverterGson = "com.squareup.retrofit2:converter-gson:${Versions.Network.RETROFIT}"
        const val retrofitAdapterRxJava = "com.squareup.retrofit2:adapter-rxjava3:${Versions.Network.RETROFIT}"
        const val retrofitAdapterCoroutines = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-experimental-adapter:${Versions.Network.RETROFIT_COROUTINE_ADAPTER}"
        const val okhttpLogInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.Network.OKHTTP_LOG_INTERCEPTOR}"
        const val chuckDebug = "com.readystatesoftware.chuck:library:${Versions.Network.CHUCK}"
        const val chuckRelease = "com.readystatesoftware.chuck:library-no-op:${Versions.Network.CHUCK}"
    }

    object Tests {
        const val junit = "junit:junit:${Versions.Tests.JUNIT}"
        const val junits = "androidx.test.ext:junit:${Versions.Tests.JUNIT_TEST}"
        const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.Tests.ESPRESSO_CORE}"
    }
}