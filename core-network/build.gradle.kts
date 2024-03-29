import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("carssok-android")
    id("carssok-hilt")
}


android {
    defaultConfig {
        buildConfigField("String", "CARSSOK_URL", getApiKey("carssok_url"))
    }
}

fun getApiKey(propertyKey: String): String {
    return gradleLocalProperties(rootDir).getProperty(propertyKey)
}

dependencies {
    implementation(project(Dep.Modules.CORE_DATASTORE))

    implementation(Dep.Kotlin.COROUTINES_ANDROID)

    implementation(Dep.Retrofit.RETROFIT)
    implementation(Dep.Retrofit.CONVERTER_GSON)
    implementation(Dep.OkHttp.LOGGING_INTERCEPTOR)

    debugImplementation(Dep.Lib.FACEBOOK_FLIPPER)
    debugImplementation(Dep.Lib.FACEBOOK_FLIPPER_NETWORK)
    debugImplementation(Dep.Lib.FACEBOOK_FLIPPER_SOLOADER)
    releaseImplementation(Dep.Lib.FACEBOOK_FLIPPER_NOOP)
}