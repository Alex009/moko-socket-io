import org.jetbrains.kotlin.resolve.compatibility

/*
 * Copyright 2020 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

plugins {
    id("com.android.application")
    id("kotlin-android")
}

android {
    compileSdk = libs.versions.compileSdk.get().toInt()
    namespace = "com.icerockdev"

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()

        applicationId = "dev.icerock.moko.samples.socketio"

        versionCode = 1
        versionName = "0.2.0"

        vectorDrawables.useSupportLibrary = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    java {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(17)
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
        getByName("debug") {
            isDebuggable = true
            applicationIdSuffix = ".debug"
        }
    }

    packaging {
        resources.excludes.add("META-INF/*.kotlin_module")
    }
}

dependencies {

    implementation(libs.appCompat)

    implementation(projects.sample.mppLibrary)
}
