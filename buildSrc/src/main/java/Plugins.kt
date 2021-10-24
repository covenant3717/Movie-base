import Plugins.ANDROID_APPLICATION
import Plugins.ANDROID_LIBRARY
import Plugins.KOTLIN
import Plugins.KOTLIN_ANDROID
import Plugins.KOTLIN_ANDROID_EXTENSIONS
import Plugins.KOTLIN_KAPT
import Plugins.NAVIGATION_SAFE_ARGS
import org.gradle.plugin.use.PluginDependenciesSpec
import org.gradle.plugin.use.PluginDependencySpec

private object Plugins {
    const val ANDROID_APPLICATION = "com.android.application"
    const val ANDROID_LIBRARY = "com.android.library"
    const val KOTLIN_ANDROID = "kotlin-android"
    const val KOTLIN_ANDROID_EXTENSIONS = "kotlin-android-extensions"
    const val KOTLIN_KAPT = "kotlin-kapt"
    const val KOTLIN = "kotlin"
    const val NAVIGATION_SAFE_ARGS = "androidx.navigation.safeargs.kotlin"
}

fun PluginDependenciesSpec.pluginAndroidApplication(): PluginDependencySpec = id(ANDROID_APPLICATION)

fun PluginDependenciesSpec.pluginAndroidLibrary(): PluginDependencySpec = id(ANDROID_LIBRARY)

fun PluginDependenciesSpec.pluginKotlin(): PluginDependencySpec = id(KOTLIN)

fun PluginDependenciesSpec.pluginKotlinAndroid(): PluginDependencySpec = id(KOTLIN_ANDROID)

fun PluginDependenciesSpec.pluginKotlinAndroidExt(): PluginDependencySpec = id(KOTLIN_ANDROID_EXTENSIONS)

fun PluginDependenciesSpec.pluginKotlinKapt(): PluginDependencySpec = id(KOTLIN_KAPT)

fun PluginDependenciesSpec.pluginNavigationSafeArgs(): PluginDependencySpec = id(NAVIGATION_SAFE_ARGS)