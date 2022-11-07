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
    pluginKotlinKapt()
    pluginHilt()
}

fun PluginDependenciesSpec.dataPlugins() {
    pluginAndroidLibrary()
    pluginKotlinAndroid()
    pluginKotlinAndroidExt()
    pluginKotlinKapt()
    pluginHilt()
}

fun PluginDependenciesSpec.domainPlugins() {
    pluginKotlin()
}

fun PluginDependenciesSpec.featurePlugins() {
    pluginAndroidLibrary()
    pluginKotlinAndroid()
    pluginKotlinAndroidExt()
    pluginKotlinKapt()
    pluginNavigationSafeArgs()
    pluginHilt()
}

