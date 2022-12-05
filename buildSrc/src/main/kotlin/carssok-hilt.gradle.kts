plugins {
    id("carssok-android")
    id("dagger.hilt.android.plugin")
}

dependencies {
    implementation(Dependency.Hilt.HILT)
    kapt(Dependency.Hilt.HILT_COMPILER)
}