plugins {
    id("carssok-hilt")
}

dependencies {
    implementation(project(Dep.Modules.CORE_DATASTORE))

    implementation(Dep.Kotlin.COROUTINES_ANDROID)
}