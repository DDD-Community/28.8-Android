plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = Version.compileSdk

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Dependency.AndroidX.Compose.compiler_version
    }
}

dependencies {
    implementation(project(Dependency.Modules.CORE_NAVIGATOR))
    implementation(Dependency.AndroidX.Navigation.COMPOSE)

    implementation(Dependency.AndroidX.CORE)
    implementation(Dependency.AndroidX.APPCOMPAT)
    implementation(Dependency.Google.MATERIAL)
    testImplementation(Dependency.JunitTest.JUNIT)
    androidTestImplementation(Dependency.AndroidXTest.ANDROID_JUNIT)
    androidTestImplementation(Dependency.AndroidXTest.ESPRESSO_CORE)
}