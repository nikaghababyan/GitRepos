@file:Suppress("UnstableApiUsage")

include(":navigation")


pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
    }
}

rootProject.name = "GitRepos"
include(":app")
include(":core:data")
include(":core:domain")
include(":core:di")
include(":core:presentation")
include(":core:ui")
include(":features:di")
include(":features:users")
include(":features:repos")
