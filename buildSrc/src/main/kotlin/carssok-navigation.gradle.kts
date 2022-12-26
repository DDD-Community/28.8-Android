plugins {
    id("carssok-android")
    id("androidx.navigation.safeargs.kotlin")
}

dependencies {
    implementation(Dep.AndroidX.Navigation.COMPOSE)
    implementation(Dep.AndroidX.Navigation.HILT_NAVIGATION_COMPOSE)
}