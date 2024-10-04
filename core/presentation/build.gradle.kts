plugins {
    id(Plugins.LIBRARY_PLUGIN)
    id(Plugins.KOTLIN_PLUGIN)
    id(Plugins.KOTLIN_KAPT)
}

android {
    namespace = "com.task.presentation"
    compileSdk = Config.COMPILE_SDK_VERSION

    defaultConfig {
        minSdk = Config.MIN_SDK_VERSION

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = Config.JVM_TARGET
    }
    sourceSets.all {
        java.srcDir("src/$name/kotlin")
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Config.KOTLIN_COMPILER_EXTENSION_VERSION
    }
    packaging {
        resources {
            excludes += Config.EXCLUDES
        }
    }
}

dependencies {
    implementation(project(":core:domain"))

    implementation(Dependencies.Compose.VIEW_MODEL)

    // Coroutines
    implementation(Dependencies.Coroutines.COROUTINES_ANDROID)
    implementation(Dependencies.Coroutines.COROUTINES_CORE)

    // Koin main features for Android
    implementation(Dependencies.Koin.KOIN_ANDROID)
    implementation(Dependencies.Koin.KOIN_COMPOSE)
    implementation(Dependencies.Koin.KOIN_CORE)
    implementation(Dependencies.Koin.KOIN_LOGGER)

    // Orbit
    implementation(Dependencies.Orbit.CORE)
    implementation(Dependencies.Orbit.VIEW_MODEL)
    implementation(Dependencies.Orbit.COMPOSE)
}