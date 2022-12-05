plugins {
    id("carssok-compose")
    id("carssok-hilt")
    id("carssok-navigation")
}

dependencies {
    implementation(Dependency.AndroidX.CORE)
    implementation(Dependency.AndroidX.APPCOMPAT)
    implementation(Dependency.Google.MATERIAL)
}