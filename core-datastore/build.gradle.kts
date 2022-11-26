plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = Version.compileSdk
}

dependencies {
    implementation(Dependency.AndroidX.DataStore.DATASTORE)

    implementation(Dependency.Hilt.HILT)
    kapt(Dependency.Hilt.HILT_COMPILER)
}