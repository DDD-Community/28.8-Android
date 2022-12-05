plugins {
    id("carssok-android")
}

dependencies {
    implementation(Dependency.Retrofit.RETROFIT)
    implementation(Dependency.Retrofit.CONVERTER_GSON)
    implementation(Dependency.OkHttp.LOGGING_INTERCEPTOR)
}