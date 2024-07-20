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

            implementation("dev.gitlive:firebase-auth:1.13.0")


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
dependencies {
    implementation(libs.firebase.auth.ktx)
}
