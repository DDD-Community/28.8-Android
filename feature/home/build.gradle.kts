plugins {
    id("carssok-feature")
}

dependencies {
    implementation(project(Dep.Modules.FEATURE_ONBOARDING))
    implementation(project(Dep.Modules.FEATURE_RECORD))

    implementation(project(Dep.Modules.CORE_DATA))
    implementation(project(Dep.Modules.CORE_NAVIGATOR))
    implementation(project(Dep.Modules.CORE_DESIGN_SYSTEM))
    implementation(project(Dep.Modules.CORE_UTIL))

    implementation(Dep.Google.SISTEM_UI_CONTROLLER)
    implementation(Dep.Lib.LANDSCAPIST)
}