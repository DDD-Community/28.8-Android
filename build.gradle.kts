// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(Dep.Gradle.GRADLE)
        classpath(Dep.Gradle.KOTLIN)
        classpath(Dep.Gradle.HILT)
    }
}

task("clean", Delete::class) {
    delete(rootProject.buildDir)
}
