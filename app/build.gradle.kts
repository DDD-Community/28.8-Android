import java.util.Properties
import java.io.FileInputStream

plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

// Create a variable called keystorePropertiesFile, and initialize it to your
// keystore.properties file, in the rootProject folder.
val keystorePropertiesFile = rootProject.file("./keystore/keystore.properties")

// Initialize a new Properties() object called keystoreProperties.
val keystoreProperties = Properties()

// Load your keystore.properties file into the keystoreProperties object.
keystoreProperties.load(FileInputStream(keystorePropertiesFile))


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

    signingConfigs {
        create("release") {
            keyAlias = keystoreProperties["keyAlias"] as String
            keyPassword = keystoreProperties["keyPassword"] as String
            storeFile = file(keystoreProperties["storeFile"] as String)
            storePassword = keystoreProperties["storePassword"] as String
        }
    }

    buildTypes {
        getByName("debug") {
            isDebuggable = true
            isMinifyEnabled = false
        }

        getByName("release") {
            signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = true
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
    implementation(project(Dep.Modules.FEATURE_HOME))
    implementation(project(Dep.Modules.FEATURE_RECORD))
    implementation(project(Dep.Modules.FEATURE_INTRODUCE))

    implementation(project(Dep.Modules.CORE_DESIGN_SYSTEM))
    implementation(project(Dep.Modules.CORE_NAVIGATOR))
    implementation(project(Dep.Modules.CORE_MODEL))
    implementation(project(Dep.Modules.CORE_NAVIGATOR))
    implementation(project(Dep.Modules.CORE_NETWORK))

    implementation(Dep.Hilt.HILT)
    kapt(Dep.Hilt.HILT_COMPILER)

    implementation(Dep.AndroidX.STARTUP)
    implementation(Dep.AndroidX.SPLASH)
    implementation(Dep.AndroidX.Navigation.COMPOSE)
    implementation(Dep.AndroidX.CORE)
    implementation(Dep.AndroidX.APPCOMPAT)
    implementation(Dep.AndroidX.CONSTRAINT_LAYOUT)
    implementation(Dep.Google.MATERIAL)

    implementation(Dep.AndroidX.Compose.MATERIAL)
    implementation(Dep.AndroidX.Compose.MATERIAL3)
    implementation(Dep.AndroidX.Compose.MATERIAL3_WINDOW_SIZE)
    implementation(Dep.AndroidX.Compose.PREVIEW_SUPPORT)
    implementation(Dep.AndroidX.Compose.UI)
    implementation(Dep.AndroidX.Compose.UI_TOOL)
    implementation(Dep.AndroidX.Compose.RUNTIME)

    implementation(platform(Dep.Firebase.BOM))
    implementation(Dep.Firebase.ANALYTICS)
}