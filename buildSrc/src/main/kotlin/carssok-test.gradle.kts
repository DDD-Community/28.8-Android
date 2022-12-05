plugins {
    id("carssok-android")
}

dependencies {
    testImplementation(Dependency.JunitTest.JUNIT)
    androidTestImplementation(Dependency.AndroidXTest.ANDROID_JUNIT)
    androidTestImplementation(Dependency.AndroidXTest.ESPRESSO_CORE)
}