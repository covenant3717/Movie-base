object Dependencies {

    object Main {
        const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.Main.KOTLIN}"
        const val kotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.Main.KOTLIN}"
        const val coreKtx = "androidx.core:core-ktx:${Versions.Main.CORE_KTX}"
        const val gradle = "com.android.tools.build:gradle:${Versions.Main.GRADLE}"
        const val navigationSafeArgPlugin = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.Jetpack.NAVIGATION}"
    }

    object DI {
        const val koin = "io.insert-koin:koin-core:${Versions.DI.KOIN}"
        const val koinAndroid = "io.insert-koin:koin-android:${Versions.DI.KOIN}"
        const val hilt = "com.google.dagger:hilt-android:${Versions.DI.HILT}"
        const val hiltCompiler = "com.google.dagger:hilt-compiler:${Versions.DI.HILT}"
        const val hiltGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.DI.HILT}"
    }

    object Coroutines {
        const val coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Coroutines.COROUTINE}"
        const val coroutineAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Coroutines.COROUTINE}"
    }

    object Jetpack {
        const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.Jetpack.LIFECYCLE}"
        const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.Jetpack.LIFECYCLE}"
        const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.Jetpack.LIFECYCLE}"
        const val appcompat = "androidx.appcompat:appcompat:${Versions.Jetpack.APP_COMPAT}"
        const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.Jetpack.NAVIGATION}"
        const val navigationUI = "androidx.navigation:navigation-ui-ktx:${Versions.Jetpack.NAVIGATION}"
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

    object Network {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.Network.RETROFIT}"
        const val retrofitConverterGson = "com.squareup.retrofit2:converter-gson:${Versions.Network.RETROFIT}"
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
        const val FEATURE_HOME = ":feature-home"
        const val FEATURE_MOVIE = ":feature-movie"
        const val FEATURE_ACTOR = ":feature-actor"
        const val FEATURE_BOTTOM_DIALOG = ":feature-bottom-dialog"
    }
}