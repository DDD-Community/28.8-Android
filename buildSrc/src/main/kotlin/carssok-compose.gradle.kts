plugins {
    id("carssok-android")
}

android {
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Dep.AndroidX.Compose.compiler_version
    }
}

dependencies {
    implementation(Dep.AndroidX.Compose.MATERIAL3)
    implementation(Dep.AndroidX.Compose.MATERIAL3_WINDOW_SIZE)
    implementation(Dep.AndroidX.Compose.PREVIEW_SUPPORT)
    implementation(Dep.AndroidX.Compose.UI)
    implementation(Dep.AndroidX.Compose.UI_TOOL)
    implementation(Dep.AndroidX.Compose.RUNTIME)
}