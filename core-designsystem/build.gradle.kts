plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = Version.compileSdk
}

dependencies {

    implementation(Dependency.AndroidX.CORE)
    implementation(Dependency.AndroidX.APPCOMPAT)
    implementation(Dependency.Google.MATERIAL)
    testImplementation(Dependency.JunitTest.JUNIT)
    androidTestImplementation(Dependency.AndroidXTest.ANDROID_JUNIT)
    androidTestImplementation(Dependency.AndroidXTest.ESPRESSO_CORE)
}