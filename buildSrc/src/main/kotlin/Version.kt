object Version {
    const val compileSdk = 33
    const val minSdk = 26
    const val targetSdk = 32

    const val appMajorVersion = 0
    const val appMinorVersion = 1
    const val appHotfixVersion = 0
    const val appAdditionalVersion = 0


    fun getVersionCode() = appMajorVersion * 1_000_000 +
            appMinorVersion * 10_000 +
            appHotfixVersion * 100 +
            appAdditionalVersion

    fun getVersionName() = "${appMajorVersion}.${appMinorVersion}.${appHotfixVersion}"
}