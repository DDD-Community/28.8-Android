plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = Version.compileSdk

    defaultConfig  {
        minSdk = Version.minSdk
    }


    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Dependency.AndroidX.Compose.compiler_version
    }
}

dependencies {
    implementation(Dependency.AndroidX.Compose.MATERIAL3)
    implementation(Dependency.AndroidX.Compose.MATERIAL3_WINDOW_SIZE)
    implementation(Dependency.AndroidX.Compose.PREVIEW_SUPPORT)
    implementation(Dependency.AndroidX.Compose.UI)
    implementation(Dependency.AndroidX.Compose.UI_TOOL)
    implementation(Dependency.AndroidX.Compose.RUNTIME)

    implementation(Dependency.AndroidX.CORE)
    implementation(Dependency.AndroidX.APPCOMPAT)
    implementation(Dependency.Google.MATERIAL)
    testImplementation(Dependency.JunitTest.JUNIT)
    androidTestImplementation(Dependency.AndroidXTest.ANDROID_JUNIT)
    androidTestImplementation(Dependency.AndroidXTest.ESPRESSO_CORE)
}