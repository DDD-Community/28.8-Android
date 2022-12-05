plugins {
    id("carssok-android")
}

android {
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
}