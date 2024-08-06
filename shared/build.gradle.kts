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

val projectSupabaseRef: String = localProperties.getProperty("PROJECT_SUPABASE_REF", "")
val apiKeySupabase: String = localProperties.getProperty("API_KEY_SUPABASE", "")
val googleClientId: String = localProperties.getProperty("GOOGLE_CLIENT_ID", "")

buildConfig {
    className = "MySecrets"
    packageName = "com.baldomeronapoli.eventplanner.shared"
    buildConfigField("ALGOLIA_API_KEY", algoliaApiKey)
    buildConfigField("ALGOLIA_APPLICATION_ID", algoliaApplicationId)
    buildConfigField("PROJECT_SUPABASE_REF", projectSupabaseRef)
    buildConfigField("API_KEY_SUPABASE", apiKeySupabase)
    buildConfigField("GOOGLE_CLIENT_ID", googleClientId)
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
            implementation(libs.ktor.client.okhttp)
        }
        commonMain.dependencies {
            // implementation(libs.kotlinx.coroutines.core)
            // implementation(libs.kotlin.reflect)

            implementation(libs.kmp.settings)
            implementation(libs.kmp.viewmodel)
            implementation(libs.kmp.kermit)
            implementation(libs.compass.geocoder)
            implementation(libs.compass.geocoder.mobile)
            implementation(libs.compass.geolocation)
            implementation(libs.compass.geolocation.mobile)
            implementation(libs.compass.autocomplete)
            implementation(libs.compass.autocomplete.mobile)

            implementation(libs.kotlinx.datetime)

            implementation(libs.kmp.koin.core)


            implementation("com.squareup.okio:okio:3.9.0")
            implementation(project.dependencies.platform("io.github.jan-tennert.supabase:bom:2.5.4"))
            implementation("io.github.jan-tennert.supabase:gotrue-kt")
            implementation("io.github.jan-tennert.supabase:realtime-kt")
            implementation("io.github.jan-tennert.supabase:storage-kt")
            implementation("io.github.jan-tennert.supabase:compose-auth")
            implementation("io.github.jan-tennert.supabase:apollo-graphql")
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
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
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}
