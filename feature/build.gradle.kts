plugins {
    id("carssok-feature")
}

dependencies {
    implementation(project(Dep.Modules.CORE_DATA))
    implementation(project(Dep.Modules.CORE_NAVIGATOR))
    implementation(project(Dep.Modules.CORE_DESIGN_SYSTEM))
    implementation(project(Dep.Modules.CORE_DATASTORE))
}