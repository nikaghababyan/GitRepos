// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
}

plugins {
    id(Plugins.APPLICATION_PLUGIN) version Plugins.APPLICATION_PLUGIN_VERSION apply false
    id(Plugins.LIBRARY_PLUGIN) version Plugins.APPLICATION_PLUGIN_VERSION apply false
    id(Plugins.KOTLIN_PLUGIN) version Plugins.KOTLIN_PLUGIN_VERSION apply false
}

tasks {
    val clean by registering(Delete::class) {
        delete(buildDir)
    }
}