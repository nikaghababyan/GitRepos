plugins {
    id(Plugins.LIBRARY_PLUGIN)
    id(Plugins.KOTLIN_PLUGIN)
    id(Plugins.KOTLIN_KAPT)
}

android {
    namespace = "com.task.data"
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

    // Coroutines
    implementation(Dependencies.Coroutines.COROUTINES_ANDROID)
    implementation(Dependencies.Coroutines.COROUTINES_CORE)

    // Preferences DataStore
    implementation(Dependencies.DataStore.DATASTORE_PREFERENCES)
    implementation(Dependencies.DataStore.DATASTORE_PREFERENCES_CORE)

    // Room
    implementation(Dependencies.Room.RUNTIME)
    implementation(Dependencies.Room.KTX)
    kapt(Dependencies.Room.COMPILER_KAPT)

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
}