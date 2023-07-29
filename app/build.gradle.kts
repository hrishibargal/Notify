plugins {
    id ("com.android.application")
    kotlin ("android")
    id ("kotlin-parcelize")
    id ("kotlin-kapt")
    id ("com.google.dagger.hilt.android")
    id ("com.google.gms.google-services")
    id ("com.google.firebase.crashlytics")
}

android {
    namespace = "com.aritra.notify"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.aritra.notify"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility =  JavaVersion.VERSION_1_8
        targetCompatibility =  JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation ("androidx.core:core-ktx:1.10.1")
    implementation (platform("org.jetbrains.kotlin:kotlin-bom:1.8.0"))
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation ("androidx.activity:activity-compose:1.7.2")
    implementation (platform("androidx.compose:compose-bom:2022.10.00"))
    implementation ("androidx.compose.ui:ui")
    implementation ("androidx.compose.ui:ui-graphics")
    implementation ("androidx.compose.ui:ui-tooling-preview")
    implementation ("androidx.compose.material3:material3")
    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.5")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation (platform("androidx.compose:compose-bom:2022.10.00"))
    androidTestImplementation ("androidx.compose.ui:ui-test-junit4")
    debugImplementation ("androidx.compose.ui:ui-tooling")
    debugImplementation ("androidx.compose.ui:ui-test-manifest")

    //Navigation
    implementation ("androidx.navigation:navigation-compose:2.7.0-beta01")

    // Accompanist
    implementation ("com.google.accompanist:accompanist-systemuicontroller:0.28.0")

    //Material 3
    implementation ("androidx.compose.material3:material3:1.1.1")
    implementation ("androidx.compose.material3:material3-window-size-class:1.1.1")

    //Bottom sheet
    implementation ("io.github.oleksandrbalan:modalsheet:0.5.0")

    //Room
    implementation ("androidx.room:room-runtime:2.5.2")
    implementation ("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation ("androidx.room:room-ktx:2.5.2")
    implementation ("androidx.compose.runtime:runtime-livedata:1.4.3")
    annotationProcessor ("androidx.room:room-compiler:2.5.2")
    kapt ("androidx.room:room-compiler:2.5.2")

    // ViewModel
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation ("androidx.core:core-ktx:1.10.1")

    //hilt
    implementation ("androidx.hilt:hilt-navigation-compose:1.0.0")
    implementation ("com.google.dagger:hilt-android:2.44")
    kapt ("com.google.dagger:hilt-compiler:2.44")

    // DataStore
    implementation ("androidx.datastore:datastore-preferences:1.0.0")

    //Swipe
    implementation ("me.saket.swipe:swipe:1.2.0")

    // Splash API
    implementation ("androidx.core:core-splashscreen:1.0.1")

    // Firebase Crashlytics
    implementation ("com.google.firebase:firebase-crashlytics-ktx:18.4.0")
    implementation ("com.google.firebase:firebase-analytics-ktx:21.3.0")

}