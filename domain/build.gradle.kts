plugins {
    id("kotlin")
}

dependencies {
    implementation(Libs.kotlin)
    implementation(Libs.coreKtx)

    implementation(Libs.RX.rxJava)
    implementation(Libs.RX.rxAndroid)
    implementation(Libs.RX.rxKotlin)
}