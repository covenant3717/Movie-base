import org.gradle.api.JavaVersion

object Versions {

    object App {
        const val COMPILE_SDK = 33
        const val APPLICATION_ID = "com.evgenykuksov.moviebase"
        const val MIN_SDK = 21
        const val TARGET_SDK = 30
        const val VERSION_CODE = 1
        const val VERSION_NAME = "1.0"
    }

    object Main {
        const val KOTLIN = "1.7.10"
        const val KOTLIN_COMPILER_EXTENSION = "1.2.0"
        const val CORE_KTX = "1.8.0"
        const val GRADLE = "7.2.1"
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
        const val COMPOSE_BOM = "2023.01.00"
        const val COMPOSE_ACTIVITY = "1.5.1"
        const val COMPOSE_VIEW_MODEL = "2.5.1"
    }

    object UI {
        const val MATERIAL = "1.4.0"
        const val COIL = "2.2.2"
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