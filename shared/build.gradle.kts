import java.util.Properties

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.nativecoroutines)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.google.ksp)
    id("com.github.gmazzo.buildconfig") version "5.4.0"
}

// Cargar propiedades desde local.properties
val localProperties = Properties().apply {
    file("local.properties").inputStream().use { load(it) }
}

val algoliaApiKey: String = localProperties.getProperty("ALGOLIA_API_KEY", "")
val algoliaApplicationId: String = localProperties.getProperty("ALGOLIA_APPLICATION_ID", "")

buildConfig {
    buildConfigField("ALGOLIA_API_KEY", algoliaApiKey)
    buildConfigField("ALGOLIA_APPLICATION_ID", algoliaApplicationId)
}
kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
            freeCompilerArgs += listOf("-Xbinary=bundleId=com.baldomeronapoli.eventplanner.shared")
        }
    }

    sourceSets {
        all {
            languageSettings.apply {
                optIn("kotlinx.cinterop.ExperimentalForeignApi")
                optIn("kotlin.experimental.ExperimentalObjCName")
            }
        }
        androidMain.dependencies {
            implementation(libs.kotlin.reflect)
            implementation(libs.kmp.koin.android)
            implementation("io.ktor:ktor-client-okhttp:2.3.0")
        }
        commonMain.dependencies {
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlin.reflect)
            implementation(libs.kotlinx.serialization)
            implementation(libs.kmp.settings)
            api(libs.kmp.viewmodel)
            api(libs.kmp.koin.core)
            api(libs.kmp.kermit)
            api(libs.firebase.auth)
            api("dev.gitlive:firebase-firestore:1.13.0")
            api("dev.gitlive:firebase-storage:1.13.0")
            implementation(libs.compass.geocoder)
            implementation(libs.compass.geocoder.mobile)
            implementation(libs.compass.geolocation)
            implementation(libs.compass.geolocation.mobile)
            implementation(libs.compass.autocomplete)
            implementation(libs.compass.autocomplete.mobile)
            implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.6.0")
            implementation("io.ktor:ktor-client-core:2.3.0")
            implementation("io.ktor:ktor-client-content-negotiation:2.3.0")
            implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.0")
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")
            implementation("io.insert-koin:koin-core:3.3.0")
        }
        iosMain.dependencies {
            implementation("io.ktor:ktor-client-darwin:2.3.0")
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "com.baldomeronapoli.eventplanner"
    compileSdk = 34
    buildFeatures {
        buildConfig = true
    }
    defaultConfig {
        minSdk = 26
        buildConfigField("String", "API_KEY", "\"${project.findProperty("api_key") ?: ""}\"")
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}
