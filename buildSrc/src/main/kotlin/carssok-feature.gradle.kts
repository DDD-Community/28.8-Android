plugins {
    id("carssok-compose")
    id("carssok-hilt")
    id("carssok-navigation")
}

dependencies {
    implementation(Dep.AndroidX.CORE)
    implementation(Dep.AndroidX.APPCOMPAT)
    implementation(Dep.Google.MATERIAL)
}