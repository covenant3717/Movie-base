import org.gradle.api.JavaVersion

object Versions {

    object App {
        const val COMPILE_SDK = 31
        const val APPLICATION_ID = "com.evgenykuksov.moviebase"
        const val MIN_SDK = 21
        const val TARGET_SDK = 30
        const val VERSION_CODE = 1
        const val VERSION_NAME = "1.0"
    }

    object Main {
        const val KOTLIN = "1.5.31"
        const val CORE_KTX = "1.3.2"
        const val GRADLE = "7.0.4"
        val JAVA = JavaVersion.VERSION_1_8
    }

    object DI {
        const val KOIN = "3.1.3"
    }

    object Coroutines {
        const val COROUTINE = "1.6.0"
    }

    object Jetpack {
        const val LIFECYCLE = "2.4.1"
        const val APP_COMPAT = "1.2.0"
        const val NAVIGATION = "2.4.2"
    }

    object UI {
        const val MATERIAL = "1.4.0"
        const val COIL = "1.4.0"
        const val COIL_GIF = "1.3.0"
        const val FRAGMENT = "1.4.0"
        const val CONSTRAINT_LAYOUT = "2.1.2"
        const val GROUPIE = "2.9.0"
        const val CIRCLE_IMAGE_VIEW = "3.1.0"
        const val SHIMMER = "0.5.0"
        const val PHOTOVIEW = "2.3.0"
    }

    object Network {
        const val RETROFIT = "2.9.0"
        const val OKHTTP_LOG_INTERCEPTOR = "4.9.0"
        const val CHUCK = "1.1.0"
    }

    object Tests {
        const val JUNIT = "4.12"
        const val JUNIT_TEST = "1.1.2"
        const val ESPRESSO_CORE = "3.3.0"
    }
}