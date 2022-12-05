plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")

}
android {
    compileSdk = Version.compileSdk

    defaultConfig  {
        minSdk = Version.minSdk
    }
}