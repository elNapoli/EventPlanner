plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.nativecoroutines)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.google.ksp)
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
            // Geolocation
            implementation(libs.compass.geolocation)
            implementation(libs.compass.geolocation.mobile)

            implementation(libs.compass.autocomplete)
            implementation(libs.compass.autocomplete.mobile)

            implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.6.0")


        }
        iosMain.dependencies {

        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "com.baldomeronapoli.eventplanner"
    compileSdk = 34
    defaultConfig {
        minSdk = 26
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}