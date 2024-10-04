import org.gradle.api.JavaVersion

object Config {
    const val COMPILE_SDK_VERSION = 34
    const val APPLICATION_ID = "com.task.gitrepos"
    const val MIN_SDK_VERSION = 23
    const val TARGET_SDK_VERSION = 34
    const val VERSION_CODE = 1
    const val VERSION_NAME = "1.0.0"
    val JVM_TARGET = JavaVersion.VERSION_17.toString()
    const val KOTLIN_COMPILER_EXTENSION_VERSION = "1.5.1"
    const val EXCLUDES = "/META-INF/{AL2.0,LGPL2.1}"
}

