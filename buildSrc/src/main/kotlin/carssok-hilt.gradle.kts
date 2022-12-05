plugins {
    id("carssok-android")
    id("dagger.hilt.android.plugin")
}

dependencies {
    implementation(Dep.Hilt.HILT)
    kapt(Dep.Hilt.HILT_COMPILER)
}