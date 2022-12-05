plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    // TODO jhp-dev : custom plugin 정의를 위해 추가, plugin version 관리 일원화
    implementation("com.android.tools.build:gradle:7.3.1")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.20")
    implementation("com.google.dagger:hilt-android-gradle-plugin:2.42")
    implementation("androidx.navigation:navigation-safe-args-gradle-plugin:2.5.3")
}