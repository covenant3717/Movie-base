object Apps {
    const val compileSdk = 30
    const val appId = "com.evgenykuksov.rercipes"
    const val minSdk = 21
    const val targetSdk = 30
    const val versionCode = 1
    const val versionName = "1.0"
}

object Versions {
    const val kotlin = "1.4.21"
    const val gradle = "4.1.1"
    const val coreKtx = "1.3.2"

    const val appCompat = "1.0.2"
    const val material = "1.2.1"

    // view
    const val constraintLayout = "2.0.4"

    // test
    const val junit = "4.12"
    const val junitTest = "1.1.2"
    const val espressoCore = "3.3.0"
}

object Libs {
    const val kotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val gradle = "com.android.tools.build:gradle:${Versions.gradle}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"

    const val appcompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val material = "com.google.android.material:material:${Versions.material}"

    // view
    const val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"

    // test
    const val junit = "junit:junit:${Versions.junit}"
    const val junits = "androidx.test.ext:junit:${Versions.junitTest}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
}