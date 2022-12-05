plugins {
    id("carssok-android")
}

dependencies {
    testImplementation(Dep.JunitTest.JUNIT)
    androidTestImplementation(Dep.AndroidXTest.ANDROID_JUNIT)
    androidTestImplementation(Dep.AndroidXTest.ESPRESSO_CORE)
}