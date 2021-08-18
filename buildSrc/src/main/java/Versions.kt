import org.gradle.api.JavaVersion

object Versions {

    object App {
        const val COMPILE_SDK = 30
        const val APPLICATION_ID = "com.evgenykuksov.moviebase"
        const val MIN_SDK = 21
        const val TARGET_SDK = 30
        const val VERSION_CODE = 1
        const val VERSION_NAME = "1.0"
    }

    object UI {
        const val APP_COMPAT = "1.2.0"
        const val MATERIAL = "1.2.1"
        const val FRAGMENT = "1.3.5"
        const val CONSTRAINT_LAYOUT = "2.0.4"
        const val GROUPIE = "2.9.0"
        const val CIRCLE_IMAGE_VIEW = "3.1.0"
        const val SHIMMER = "0.5.0"
    }

    object Network {
        const val RETROFIT = "2.9.0"
        const val RETROFIT_COROUTINE_ADAPTER = "1.0.0"
        const val OKHTTP_LOG_INTERCEPTOR = "4.9.0"
        const val CHUCK = "1.1.0"
    }

    object Threads {
        const val COROUTINE = "1.4.2"
        const val RX_JAVA = "3.0.11"
        const val RX_ANDROID = "3.0.0"
        const val RX_KOTLIN = "3.0.1"
    }

    object Tests {
        const val JUNIT = "4.12"
        const val JUNIT_TEST = "1.1.2"
        const val ESPRESSO_CORE = "3.3.0"
    }

    const val KOTLIN = "1.5.20"
    const val GRADLE = "4.1.1"
    const val CORE_KTX = "1.3.2"
    val JAVA = JavaVersion.VERSION_1_8

    const val LIFECYCLE = "2.2.0"
    const val KOIN = "2.2.2"
    const val COIL = "1.2.2"
    const val COIL_GIF = "1.3.0"
}