plugins {
    id("carssok-hilt")
}

dependencies {
    implementation(project(Dep.Modules.CORE_MODEL))
    implementation(project(Dep.Modules.CORE_DATASTORE))
    implementation(project(Dep.Modules.CORE_NETWORK))
    implementation(project(Dep.Modules.CORE_MODEL))

    implementation(Dep.Kotlin.COROUTINES_ANDROID)
    implementation(Dep.AndroidX.DataStore.DATASTORE)
    implementation(Dep.Retrofit.OKHTTP)
    implementation(Dep.Retrofit.RETROFIT)
}