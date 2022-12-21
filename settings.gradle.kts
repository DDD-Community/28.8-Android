pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "carssok"
include(":app")
include(":core-network")
include(":core-designsystem")
include(":core-navigator")
include(":core-datastore")
include(":core-data")
include(":feature:home")
include(":feature:record")
include(":feature:introduce")
include(":feature:onboarding")
