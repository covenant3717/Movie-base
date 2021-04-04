plugins {
    id("kotlin")
}

dependencies {
    implementation(Libs.kotlin)
    implementation(Libs.coreKtx)

    implementation(Libs.Coroutines.coroutine)
    implementation(Libs.Coroutines.coroutineAndroid)
}