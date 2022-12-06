plugins {
    id("carssok-android")
}

dependencies {
    implementation(Dep.Retrofit.RETROFIT)
    implementation(Dep.Retrofit.CONVERTER_GSON)
    implementation(Dep.OkHttp.LOGGING_INTERCEPTOR)
}