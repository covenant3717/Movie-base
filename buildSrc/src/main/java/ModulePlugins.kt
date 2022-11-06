import org.gradle.plugin.use.PluginDependenciesSpec

fun PluginDependenciesSpec.appPlugins() {
    pluginAndroidApplication()
    pluginKotlinAndroid()
    pluginKotlinAndroidExt()
    pluginKotlinKapt()
    pluginNavigationSafeArgs()
    pluginHilt()
}

fun PluginDependenciesSpec.corePlugins() {
    pluginAndroidLibrary()
    pluginKotlinAndroid()
    pluginKotlinAndroidExt()
}

fun PluginDependenciesSpec.dataPlugins() {
    pluginAndroidLibrary()
    pluginKotlinAndroid()
    pluginKotlinAndroidExt()
    pluginKotlinKapt()
}

fun PluginDependenciesSpec.domainPlugins() {
    pluginKotlin()
}

fun PluginDependenciesSpec.featurePlugins() {
    pluginAndroidLibrary()
    pluginKotlinAndroid()
    pluginKotlinAndroidExt()
}

