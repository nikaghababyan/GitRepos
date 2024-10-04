plugins {
    id(Plugins.APPLICATION_PLUGIN)
    id(Plugins.KOTLIN_PLUGIN)
    id(Plugins.KOTLIN_KAPT)
    id(Plugins.PARCELIZE)
}

android {
    namespace = Config.APPLICATION_ID
    compileSdk = Config.COMPILE_SDK_VERSION

    defaultConfig {
        applicationId = Config.APPLICATION_ID
        minSdk = Config.MIN_SDK_VERSION
        targetSdk = Config.TARGET_SDK_VERSION
        versionCode = Config.VERSION_CODE
        versionName = Config.VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        kapt {
            arguments {
                arg("room.schemaLocation", "$projectDir/schemas")
            }
        }
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "GIT_TOKEN", "\"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\"")
            buildConfigField("String", "BASE_URL", "\"https://api.github.com/\"")
        }
        debug {
            isDebuggable = true
            isMinifyEnabled = false
            buildConfigField("String", "GIT_TOKEN", "\"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\"")
            buildConfigField("String", "BASE_URL", "\"https://api.github.com/\"")
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
    implementation(project(":core:data"))
    implementation(project(":core:di"))
    implementation(project(":core:ui"))
    implementation(project(":navigation"))

    // Compose
    val composeBoomPlatform = platform(Dependencies.Compose.BOOM)
    implementation(composeBoomPlatform)
    implementation(Dependencies.Compose.UI)
    implementation(Dependencies.Compose.MATERIAL_2)
    implementation(Dependencies.Compose.PREVIEW_TOOLING)
    implementation(Dependencies.Compose.FOUNDATION)
    implementation(Dependencies.Compose.MATERIAL_ICONS_CORE)
    implementation(Dependencies.Compose.MATERIAL_ICONS_EXTENDED)
    implementation(Dependencies.Compose.CONSTRAINT_LAYOUT)
    implementation(Dependencies.Compose.VIEW_MODEL)
    implementation(Dependencies.Compose.MATERIAL_3)
    implementation(Dependencies.Compose.ACCOMPANIST_SYSTEM_UI_CONTROLLER)
    implementation(Dependencies.Compose.ACCOMPANIST_PERMISSION)
    implementation(Dependencies.Compose.ACTIVITY_COMPOSE)
    implementation(Dependencies.Compose.LIFECYCLE_RUNTIME_COMPOSE)
    implementation(Dependencies.Compose.NAVIGATION)
    implementation(Dependencies.Android.KTX_CORE)
    implementation(Dependencies.Android.RUNTIME_LIFECYCLE)
    implementation(Dependencies.Compose.VIEW_BINDING)
    implementation(Dependencies.Compose.PAGING_3_COMPOSE)
    debugImplementation(Dependencies.Compose.UI_TOOLING)
    implementation(Dependencies.Sdp.ANDROID)

    // Koin main features for Android
    implementation(Dependencies.Koin.KOIN_ANDROID)
    implementation(Dependencies.Koin.KOIN_COMPOSE)
    implementation(Dependencies.Koin.KOIN_CORE)
    implementation(Dependencies.Koin.KOIN_LOGGER)
}