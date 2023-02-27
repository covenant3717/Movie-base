import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.project

fun DependencyHandlerScope.core() {
    "implementation"(Dependencies.Main.kotlin)
    "implementation"(Dependencies.Main.coreKtx)
    "implementation"(Dependencies.DI.koin)
    "implementation"(Dependencies.DI.koinAndroid)
    "implementation"(Dependencies.Coroutines.coroutine)
    "implementation"(Dependencies.Coroutines.coroutineAndroid)
    "implementation"(platform(Dependencies.Jetpack.composeBOM))
    "implementation"(Dependencies.Jetpack.composeMaterial)
    "implementation"(Dependencies.Jetpack.composeFoundation)
    "implementation"(Dependencies.Jetpack.composeUI)
    "implementation"(Dependencies.Jetpack.composeToolingPreview)
    "debugImplementation"(Dependencies.Jetpack.composeTooling)
    "implementation"(Dependencies.Jetpack.viewmodel)
    "implementation"(Dependencies.Jetpack.livedata)
    "implementation"(Dependencies.Jetpack.runtime)
    "implementation"(Dependencies.Jetpack.appcompat)
    "implementation"(Dependencies.UI.material)
    "implementation"(Dependencies.UI.coil)
    "implementation"(Dependencies.UI.coilGif)
    "implementation"(Dependencies.UI.constraintLayout)
    "implementation"(Dependencies.UI.groupie)
    "implementation"(Dependencies.UI.groupieExt)
    "implementation"(Dependencies.UI.shimmer)
}

fun DependencyHandlerScope.domain() {
    "implementation"(Dependencies.Main.kotlin)
    "implementation"(Dependencies.Coroutines.coroutine)
    "implementation"(Dependencies.Coroutines.coroutineAndroid)
}

fun DependencyHandlerScope.data() {
    "implementation"(project(Dependencies.Modules.CORE))
    "implementation"(project(Dependencies.Modules.DOMAIN))

    "implementation"(Dependencies.Main.kotlin)
    "implementation"(Dependencies.Main.coreKtx)
    "implementation"(Dependencies.DI.koin)
    "implementation"(Dependencies.DI.koinAndroid)
    "implementation"(Dependencies.Coroutines.coroutine)
    "implementation"(Dependencies.Coroutines.coroutineAndroid)
    "implementation"(Dependencies.Network.retrofit)
    "implementation"(Dependencies.Network.retrofitConverterGson)
    "implementation"(Dependencies.Network.okhttpLogInterceptor)
    "debugImplementation"(Dependencies.Network.chuckDebug)
    "releaseImplementation"(Dependencies.Network.chuckRelease)
}

fun DependencyHandlerScope.app() {
    "implementation"(project(Dependencies.Modules.CORE))
    "implementation"(project(Dependencies.Modules.DOMAIN))
    "implementation"(project(Dependencies.Modules.DATA))
    "implementation"(project(Dependencies.Modules.FEATURE_HOME))
    "implementation"(project(Dependencies.Modules.FEATURE_MOVIE))
    "implementation"(project(Dependencies.Modules.FEATURE_ACTOR))
    "implementation"(project(Dependencies.Modules.FEATURE_BOTTOM_DIALOG))

    "implementation"(Dependencies.Main.kotlin)
    "implementation"(Dependencies.Main.coreKtx)
    "implementation"(Dependencies.DI.koin)
    "implementation"(Dependencies.DI.koinAndroid)
    "implementation"(Dependencies.Coroutines.coroutine)
    "implementation"(Dependencies.Coroutines.coroutineAndroid)
    "implementation"(Dependencies.Jetpack.appcompat)
    "implementation"(Dependencies.Jetpack.navigationFragment)
    "implementation"(Dependencies.Jetpack.navigationUI)
    "implementation"(platform(Dependencies.Jetpack.composeBOM))
    "implementation"(Dependencies.Jetpack.composeMaterial)
    "implementation"(Dependencies.Jetpack.composeFoundation)
    "implementation"(Dependencies.Jetpack.composeUI)
    "implementation"(Dependencies.Jetpack.composeToolingPreview)
    "debugImplementation"(Dependencies.Jetpack.composeTooling)
    "implementation"(Dependencies.Jetpack.composeActivity)
    "implementation"(Dependencies.Jetpack.composeViewModel)
    "implementation"(Dependencies.UI.constraintLayout)
    "implementation"(Dependencies.UI.groupie)
    "implementation"(Dependencies.UI.groupieExt)
}

private fun DependencyHandlerScope.commonFeatureDependencies() {
    "implementation"(project(Dependencies.Modules.CORE))
    "implementation"(project(Dependencies.Modules.DOMAIN))
    "implementation"(project(Dependencies.Modules.DATA))

    "implementation"(Dependencies.Main.kotlin)
    "implementation"(Dependencies.Main.coreKtx)
    "implementation"(Dependencies.DI.koin)
    "implementation"(Dependencies.DI.koinAndroid)
    "implementation"(Dependencies.Coroutines.coroutine)
    "implementation"(Dependencies.Coroutines.coroutineAndroid)
    "implementation"(Dependencies.Jetpack.viewmodel)
    "implementation"(Dependencies.Jetpack.livedata)
    "implementation"(Dependencies.Jetpack.runtime)
    "implementation"(Dependencies.Jetpack.appcompat)
    "implementation"(Dependencies.Jetpack.navigationFragment)
    "implementation"(Dependencies.Jetpack.navigationUI)
    "implementation"(platform(Dependencies.Jetpack.composeBOM))
    "implementation"(Dependencies.Jetpack.composeMaterial)
    "implementation"(Dependencies.Jetpack.composeFoundation)
    "implementation"(Dependencies.Jetpack.composeUI)
    "implementation"(Dependencies.Jetpack.composeToolingPreview)
    "debugImplementation"(Dependencies.Jetpack.composeTooling)
    "implementation"(Dependencies.Jetpack.composeActivity)
    "implementation"(Dependencies.Jetpack.composeViewModel)
    "implementation"(Dependencies.UI.material)
    "implementation"(Dependencies.UI.coilCompose)
    "implementation"(Dependencies.UI.coilGif)
    "implementation"(Dependencies.UI.constraintLayout)
    "implementation"(Dependencies.UI.fragment)
    "implementation"(Dependencies.UI.fragmentKts)
    "implementation"(Dependencies.UI.groupie)
    "implementation"(Dependencies.UI.groupieExt)
    "implementation"(Dependencies.UI.circleImageView)
    "implementation"(Dependencies.UI.shimmer)
    "implementation"(Dependencies.UI.photoView)
}

fun DependencyHandlerScope.featureHome() {
    commonFeatureDependencies()
}

fun DependencyHandlerScope.featureMovie() {
    commonFeatureDependencies()
}

fun DependencyHandlerScope.featureActor() {
    commonFeatureDependencies()
}

fun DependencyHandlerScope.featureBottomDialog() {
    commonFeatureDependencies()
}
