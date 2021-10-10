import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.project

object DependenciesModule {

    fun DependencyHandlerScope.core() {
        "implementation"(Dependencies.kotlin)
        "implementation"(Dependencies.coreKtx)

        "implementation"(Dependencies.Jetpack.viewmodel)
        "implementation"(Dependencies.Jetpack.livedata)
        "implementation"(Dependencies.Jetpack.runtime)

        "implementation"(Dependencies.UI.appcompat)
        "implementation"(Dependencies.UI.material)
        "implementation"(Dependencies.UI.constraintLayout)
        "implementation"(Dependencies.UI.groupie)
        "implementation"(Dependencies.UI.groupieExt)
        "implementation"(Dependencies.UI.shimmer)

        "implementation"(Dependencies.Coroutines.coroutine)
    }

    fun DependencyHandlerScope.data() {
        "implementation"(project(Dependencies.Modules.CORE))
        "implementation"(project(Dependencies.Modules.DOMAIN))

        "implementation"(Dependencies.kotlin)
        "implementation"(Dependencies.coreKtx)

        "implementation"(Dependencies.Di.koin)
        "implementation"(Dependencies.Di.koinAndroid)

        "implementation"(Dependencies.Coroutines.coroutine)
        "implementation"(Dependencies.Coroutines.coroutineAndroid)

        "implementation"(Dependencies.Network.retrofit)
        "implementation"(Dependencies.Network.retrofitConverterGson)
        "implementation"(Dependencies.Network.retrofitAdapterRxJava)
        "implementation"(Dependencies.Network.okhttpLogInterceptor)

        "debugImplementation"(Dependencies.Network.chuckDebug)
        "releaseImplementation"(Dependencies.Network.chuckRelease)
    }

    fun DependencyHandlerScope.domain() {
        "implementation"(Dependencies.kotlin)
        "implementation"(Dependencies.coreKtx)

        "implementation"(Dependencies.Coroutines.coroutine)
        "implementation"(Dependencies.Coroutines.coroutineAndroid)
    }

    fun DependencyHandlerScope.presentation() {
        "implementation"(project(Dependencies.Modules.CORE))
        "implementation"(project(Dependencies.Modules.DOMAIN))
        "implementation"(project(Dependencies.Modules.DATA))

        "implementation"(Dependencies.kotlin)
        "implementation"(Dependencies.coreKtx)
        "implementation"(Dependencies.coil)
        "implementation"(Dependencies.coilGif)

        "implementation"(Dependencies.Jetpack.viewmodel)
        "implementation"(Dependencies.Jetpack.livedata)
        "implementation"(Dependencies.Jetpack.runtime)

        "implementation"(Dependencies.UI.appcompat)
        "implementation"(Dependencies.UI.material)
        "implementation"(Dependencies.UI.constraintLayout)
        "implementation"(Dependencies.UI.fragment)
        "implementation"(Dependencies.UI.fragmentKts)
        "implementation"(Dependencies.UI.groupie)
        "implementation"(Dependencies.UI.groupieExt)
        "implementation"(Dependencies.UI.circleImageView)
        "implementation"(Dependencies.UI.shimmer)
        "implementation"(Dependencies.UI.photoView)

        "implementation"(Dependencies.Di.koin)
        "implementation"(Dependencies.Di.koinAndroid)

        "implementation"(Dependencies.Coroutines.coroutine)
        "implementation"(Dependencies.Coroutines.coroutineAndroid)

        "testImplementation"(Dependencies.Tests.junit)
        "androidTestImplementation"(Dependencies.Tests.junits)
        "androidTestImplementation"(Dependencies.Tests.espressoCore)
    }

    private fun DependencyHandlerScope.commonFeatureDependencies() {
        "implementation"(project(Dependencies.Modules.CORE))
        "implementation"(project(Dependencies.Modules.DOMAIN))
        "implementation"(project(Dependencies.Modules.DATA))

        "implementation"(Dependencies.kotlin)
        "implementation"(Dependencies.coreKtx)
        "implementation"(Dependencies.coil)
        "implementation"(Dependencies.coilGif)

        "implementation"(Dependencies.Jetpack.viewmodel)
        "implementation"(Dependencies.Jetpack.livedata)
        "implementation"(Dependencies.Jetpack.runtime)

        "implementation"(Dependencies.UI.appcompat)
        "implementation"(Dependencies.UI.material)
        "implementation"(Dependencies.UI.constraintLayout)
        "implementation"(Dependencies.UI.fragment)
        "implementation"(Dependencies.UI.fragmentKts)
        "implementation"(Dependencies.UI.groupie)
        "implementation"(Dependencies.UI.groupieExt)
        "implementation"(Dependencies.UI.circleImageView)
        "implementation"(Dependencies.UI.shimmer)
        "implementation"(Dependencies.UI.photoView)

        "implementation"(Dependencies.Di.koin)
        "implementation"(Dependencies.Di.koinAndroid)

        "implementation"(Dependencies.Coroutines.coroutine)
        "implementation"(Dependencies.Coroutines.coroutineAndroid)

        "testImplementation"(Dependencies.Tests.junit)
        "androidTestImplementation"(Dependencies.Tests.junits)
        "androidTestImplementation"(Dependencies.Tests.espressoCore)
    }

//    fun DependencyHandlerScope.featureSplash() {
//        "implementation"(project(Dependencies.Modules.PRESENTATION)) // todo: remove after integrate Jetpack navigation
//        commonFeatureDependencies()
//    }
}