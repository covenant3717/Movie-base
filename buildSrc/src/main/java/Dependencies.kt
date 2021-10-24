object Dependencies {

    object Main {
        const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.Main.KOTLIN}"
        const val kotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.Main.KOTLIN}"
        const val coreKtx = "androidx.core:core-ktx:${Versions.Main.CORE_KTX}"
        const val gradle = "com.android.tools.build:gradle:${Versions.Main.GRADLE}"
    }

    object DI {
        const val koin = "io.insert-koin:koin-core:${Versions.DI.KOIN}"
        const val koinAndroid = "io.insert-koin:koin-android:${Versions.DI.KOIN}"
    }

    object Jetpack {
        const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.Jetpack.LIFECYCLE}"
        const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.Jetpack.LIFECYCLE}"
        const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.Jetpack.LIFECYCLE}"
        const val appcompat = "androidx.appcompat:appcompat:${Versions.Jetpack.APP_COMPAT}"
        const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.Jetpack.NAVIGATION}"
        const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.Jetpack.NAVIGATION}"
    }

    object UI {
        const val material = "com.google.android.material:material:${Versions.UI.MATERIAL}"
        const val coil = "io.coil-kt:coil:${Versions.UI.COIL}"
        const val coilGif = "io.coil-kt:coil-gif:${Versions.UI.COIL_GIF}"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.UI.CONSTRAINT_LAYOUT}"
        const val fragment = "androidx.fragment:fragment:${Versions.UI.FRAGMENT}"
        const val fragmentKts = "androidx.fragment:fragment-ktx:${Versions.UI.FRAGMENT}"
        const val groupie = "com.github.lisawray.groupie:groupie:${Versions.UI.GROUPIE}"
        const val groupieExt = "com.github.lisawray.groupie:groupie-kotlin-android-extensions:${Versions.UI.GROUPIE}"
        const val circleImageView = "de.hdodenhof:circleimageview:${Versions.UI.CIRCLE_IMAGE_VIEW}"
        const val shimmer = "com.facebook.shimmer:shimmer:${Versions.UI.SHIMMER}"
        const val photoView = "com.github.chrisbanes:PhotoView:${Versions.UI.PHOTOVIEW}"
    }

    object Coroutines {
        const val coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Coroutines.COROUTINE}"
        const val coroutineAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Coroutines.COROUTINE}"
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

    object Modules {
        const val CORE = ":core"
        const val DOMAIN = ":domain"
        const val DATA = ":data"
        const val FEATURE_MAIN = ":feature-main"
        const val FEATURE_OVERVIEW = ":feature-overview"
        const val FEATURE_MOVIE = ":feature-movie"
        const val FEATURE_ACTOR = ":feature-actor"
    }
}