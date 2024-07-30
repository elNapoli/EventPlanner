plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.gms.googleService)
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

    implementation(platform("com.google.firebase:firebase-bom:32.3.1"))
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("com.google.android.gms:play-services-location:21.0.0")

    implementation("io.coil-kt:coil-compose:2.7.0")

}