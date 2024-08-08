plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.baldomeronapoli.eventplanner.android"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.baldomeronapoli.eventplanner.android"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(projects.shared)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material3)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.constraintlayout)
    debugImplementation(libs.compose.ui.tooling)

    implementation(libs.kmp.koin.android)
    implementation(libs.koin.androidx.compose)

    implementation(libs.androidx.navigation.compose)

    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.core.splashscreen)

    implementation(libs.androidx.material.icons.extended)
    implementation("org.jetbrains.kotlin:kotlin-reflect:2.0.0")
    implementation(libs.accompanist.permissions)
    implementation("io.coil-kt:coil:2.7.0")
    implementation("com.mohamedrejeb.richeditor:richeditor-compose:1.0.0-rc05-k2")
    implementation("com.google.maps.android:maps-compose:4.4.1")
    // Otras dependencias

    implementation("io.coil-kt:coil-compose:2.7.0")

    implementation("com.google.code.gson:gson:2.8.8")
    implementation(libs.kmp.viewmodel)


    implementation("androidx.credentials:credentials:1.2.2")
    implementation("com.google.android.libraries.identity.googleid:googleid:1.1.1")

// optional - needed for credentials support from play services, for devices running
// Android 13 and below.
    implementation("androidx.credentials:credentials-play-services-auth:1.2.2")
    implementation(libs.kotlinx.datetime)

    implementation("com.baldomero.napoli.eventplanner:core:1.0.1")
    implementation("com.baldomero.napoli:common:1.0.0")
    implementation("com.baldomero.napoli.eventplanner:onboarding:1.0.0")


}