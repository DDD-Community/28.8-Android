// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(Dependency.Gradle.GRADLE)
        classpath(Dependency.Gradle.KOTLIN)
        classpath(Dependency.Gradle.HILT)
    }
}

task("clean", Delete::class) {
    delete(rootProject.buildDir)
}
