plugins {
    id(Plugins.LIBRARY_PLUGIN)
    id(Plugins.KOTLIN_PLUGIN)
    id(Plugins.KOTLIN_KAPT)
}

android {
    namespace = "com.task.features.repos"
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
    implementation(project(":core:data"))
    implementation(project(":core:domain"))
    implementation(project(":core:presentation"))
    implementation(project(":core:ui"))

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

    // image loader coil
    implementation(Dependencies.Coil.COIL_COMPOSE)
    // Toast
    implementation(Dependencies.Toasty.SWEET_TOAST)

    // Coroutines
    implementation(Dependencies.Coroutines.COROUTINES_ANDROID)
    implementation(Dependencies.Coroutines.COROUTINES_CORE)

    //Time
    implementation(Dependencies.JodaTime.JODA_TIME)

    // Koin main features for Android
    implementation(Dependencies.Koin.KOIN_ANDROID)
    implementation(Dependencies.Koin.KOIN_COMPOSE)
    implementation(Dependencies.Koin.KOIN_CORE)
    implementation(Dependencies.Koin.KOIN_LOGGER)

    // Retrofit
    implementation(Dependencies.Retrofit.RETROFIT)
    implementation(Dependencies.Retrofit.GSON_CONVERTER)
    // okHttpClient
    implementation(Dependencies.OkhttpClient.OKHTTP_CLIENT)
    implementation(Dependencies.OkhttpClient.LOGGING_INTERCEPTOR)
    // Orbit
    implementation(Dependencies.Orbit.CORE)
    implementation(Dependencies.Orbit.VIEW_MODEL)
    implementation(Dependencies.Orbit.COMPOSE)
}