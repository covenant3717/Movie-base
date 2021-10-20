import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.project

object DependenciesModule {

    fun DependencyHandlerScope.core() {
        "implementation"(Dependencies.Rest.kotlin)
        "implementation"(Dependencies.Rest.coreKtx)
        "implementation"(Dependencies.Rest.coil)
        "implementation"(Dependencies.Rest.coilGif)
        "implementation"(Dependencies.Di.koin)
        "implementation"(Dependencies.Di.koinAndroid)
        "implementation"(Dependencies.Jetpack.viewmodel)
        "implementation"(Dependencies.Jetpack.livedata)
        "implementation"(Dependencies.Jetpack.runtime)
        "implementation"(Dependencies.Coroutines.coroutine)
        "implementation"(Dependencies.UI.appcompat)
        "implementation"(Dependencies.UI.material)
        "implementation"(Dependencies.UI.constraintLayout)
        "implementation"(Dependencies.UI.groupie)
        "implementation"(Dependencies.UI.groupieExt)
        "implementation"(Dependencies.UI.shimmer)
    }

    fun DependencyHandlerScope.data() {
        "implementation"(project(Dependencies.Modules.CORE))
        "implementation"(project(Dependencies.Modules.DOMAIN))
        "implementation"(Dependencies.Rest.kotlin)
        "implementation"(Dependencies.Rest.coreKtx)
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
        "implementation"(Dependencies.Rest.kotlin)
        "implementation"(Dependencies.Rest.coreKtx)
        "implementation"(Dependencies.Coroutines.coroutine)
        "implementation"(Dependencies.Coroutines.coroutineAndroid)
    }

    fun DependencyHandlerScope.app() {
        "implementation"(project(Dependencies.Modules.CORE))
        "implementation"(project(Dependencies.Modules.DOMAIN))
        "implementation"(project(Dependencies.Modules.DATA))
        "implementation"(project(Dependencies.Modules.FEATURE_MAIN))
        "implementation"(project(Dependencies.Modules.FEATURE_OVERVIEW))
        "implementation"(project(Dependencies.Modules.FEATURE_MOVIE))
        "implementation"(project(Dependencies.Modules.FEATURE_ACTOR))

        "implementation"(Dependencies.Rest.kotlin)
        "implementation"(Dependencies.Rest.coreKtx)
        "implementation"(Dependencies.Rest.coil)
        "implementation"(Dependencies.Rest.coilGif)
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

        "implementation"(Dependencies.Rest.kotlin)
        "implementation"(Dependencies.Rest.coreKtx)
        "implementation"(Dependencies.Rest.coil)
        "implementation"(Dependencies.Rest.coilGif)
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

    fun DependencyHandlerScope.featureMain() {
        commonFeatureDependencies()
    }
    fun DependencyHandlerScope.featureOverview() {
        commonFeatureDependencies()
    }
    fun DependencyHandlerScope.featureMovie() {
        commonFeatureDependencies()
    }
    fun DependencyHandlerScope.featureActor() {
        commonFeatureDependencies()
    }
}