/*
 * Copyright 2020 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

plugins {
    id("io.gitlab.arturbosch.detekt") version("1.15.0") apply(false)
}

buildscript {
    dependencies {
        classpath("dev.icerock.moko:resources-generator:0.15.1")

        classpath("dev.icerock:mobile-multiplatform:0.10.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.10")
        classpath("com.android.tools.build:gradle:4.2.1")
    }
}

allprojects {

    apply(plugin = "io.gitlab.arturbosch.detekt")

    configure<io.gitlab.arturbosch.detekt.extensions.DetektExtension> {
        input.setFrom("src/commonMain/kotlin", "src/androidMain/kotlin", "src/iosMain/kotlin", "src/commonJvm/kotlin")
    }

    dependencies {
        "detektPlugins"(rootProject.libs.detektFormatting)
    }

    plugins.withId("com.android.library") {
        configure<com.android.build.gradle.LibraryExtension> {
            compileSdkVersion(libs.versions.compileSdk.get().toInt())

            defaultConfig {
                minSdkVersion(libs.versions.minSdk.get().toInt())
                targetSdkVersion(libs.versions.targetSdk.get().toInt())
            }
        }
    }
}

tasks.register("clean", Delete::class).configure {
    group = "build"
    delete(rootProject.buildDir)
}
