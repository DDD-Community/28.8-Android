plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
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
}

dependencies {

    implementation(Dependency.AndroidX.CORE)
    implementation(Dependency.AndroidX.APPCOMPAT)
    implementation(Dependency.AndroidX.CONSTRAINT_LAYOUT)
    implementation(Dependency.Google.MATERIAL)
    testImplementation(Dependency.JunitTest.JUNIT)
    androidTestImplementation(Dependency.AndroidXTest.ANDROID_JUNIT)
    androidTestImplementation(Dependency.AndroidXTest.ESPRESSO_CORE)
}