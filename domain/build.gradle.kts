plugins {
    id(Plugins.KOTLIN)
}

dependencies {
    implementation(Dependencies.kotlin)
    implementation(Dependencies.coreKtx)

    implementation(Dependencies.Coroutines.coroutine)
    implementation(Dependencies.Coroutines.coroutineAndroid)
}