plugins {
    id("carssok-compose")
}

dependencies {
    implementation(project(Dep.Modules.CORE_UTIL))

    implementation(Dep.AndroidX.CORE)
    implementation(Dep.AndroidX.APPCOMPAT)
    implementation(Dep.Google.MATERIAL)
    implementation(Dep.Lib.LANDSCAPIST)
}