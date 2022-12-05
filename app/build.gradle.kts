plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = Version.compileSdk

    defaultConfig {
        applicationId = "com.ddd.carssok"
        minSdk = Version.minSdk
        targetSdk = Version.targetSdk
        versionCode = Version.versionCode
        versionName = Version.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_11)
        targetCompatibility(JavaVersion.VERSION_11)
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Dep.AndroidX.Compose.compiler_version
    }
}

dependencies {
    implementation(project(Dep.Modules.CORE_DESIGN_SYSTEM))
    implementation(project(Dep.Modules.FEATURE))
    implementation(project(Dep.Modules.CORE_NAVIGATOR))

    implementation(Dep.AndroidX.Navigation.COMPOSE)
    implementation(Dep.AndroidX.CORE)
    implementation(Dep.AndroidX.APPCOMPAT)
    implementation(Dep.AndroidX.CONSTRAINT_LAYOUT)
    implementation(Dep.Google.MATERIAL)
}