plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation("com.android.tools.build:gradle:7.3.1")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.20")
    implementation("com.google.dagger:hilt-android-gradle-plugin:2.42")
    implementation("androidx.navigation:navigation-safe-args-gradle-plugin:2.5.3")
}