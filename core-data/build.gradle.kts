plugins {
    id("carssok-hilt")
}

dependencies {
    implementation(project(Dep.Modules.CORE_DATASTORE))
    implementation(project(Dep.Modules.CORE_NETWORK))

    implementation(Dep.Kotlin.COROUTINES_ANDROID)
    implementation(Dep.AndroidX.DataStore.DATASTORE)
}