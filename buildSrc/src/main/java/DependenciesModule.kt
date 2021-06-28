import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.project

object DependenciesModule {

    fun DependencyHandlerScope.presentation() {
        "implementation"(project(":domain"))
        "implementation"(project(":data"))

        "implementation"(Dependencies.kotlin)
        "implementation"(Dependencies.coreKtx)
        "implementation"(Dependencies.coil)

        "implementation"(Dependencies.Jetpack.viewmodel)
        "implementation"(Dependencies.Jetpack.livedata)
        "implementation"(Dependencies.Jetpack.runtime)

        "implementation"(Dependencies.UI.appcompat)
        "implementation"(Dependencies.UI.material)
        "implementation"(Dependencies.UI.constraintLayout)
        "implementation"(Dependencies.UI.groupie)
        "implementation"(Dependencies.UI.groupieExt)
        "implementation"(Dependencies.UI.circleImageView)

        "implementation"(Dependencies.Di.koin)
        "implementation"(Dependencies.Di.koinAndroid)
        "implementation"(Dependencies.Di.koinViewModel)

        "implementation"(Dependencies.Coroutines.coroutine)
        "implementation"(Dependencies.Coroutines.coroutineAndroid)

        "testImplementation"(Dependencies.Tests.junit)
        "androidTestImplementation"(Dependencies.Tests.junits)
        "androidTestImplementation"(Dependencies.Tests.espressoCore)
    }

    fun DependencyHandlerScope.domain() {
        "implementation"(Dependencies.kotlin)
        "implementation"(Dependencies.coreKtx)

        "implementation"(Dependencies.Coroutines.coroutine)
        "implementation"(Dependencies.Coroutines.coroutineAndroid)
    }
}