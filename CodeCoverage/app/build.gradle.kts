/*
 * Copyright (C) 2024 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.android.build.api.artifact.ScopedArtifact
import com.android.build.api.variant.AndroidComponentsExtension
import com.android.build.api.variant.ScopedArtifacts
import java.util.Locale

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.example.android.codecoverage"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.android.codecoverage"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        // Enable code coverage in AGP
        debug {
            enableAndroidTestCoverage = true // Instrumented
            enableUnitTestCoverage = true // Local
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    setupCombinedReportTestCoverage()
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

/**
 * Replace with https://issuetracker.google.com/329683722 when available.
 */
fun setupCombinedReportTestCoverage() {
    val coverageExclusions = listOf(
        // Android
        "**/R.class",
        "**/R\$*.class",
        "**/BuildConfig.*",
        "**/Manifest*.*",
        "**/*_Hilt*.class",
        "**/Hilt_*.class",
    )

    val androidComponents =
        project.extensions.getByType(AndroidComponentsExtension::class.java)

    androidComponents.onVariants { variant ->
        val projectObjFactory = project.objects
        val buildDir = layout.buildDirectory.get().asFile
        val allJars: ListProperty<RegularFile> = projectObjFactory.listProperty(RegularFile::class.java)
        val allDirectories: ListProperty<Directory> = projectObjFactory.listProperty(Directory::class.java)
        val reportTask =
            tasks.register("create${variant.name.capitalize()}CombinedCoverageReport", JacocoReport::class) {

                classDirectories.setFrom(
                    allJars,
                    allDirectories.map { dirs ->
                        dirs.map { dir ->
                            projectObjFactory.fileTree().setDir(dir).exclude(coverageExclusions)
                        }
                    }
                )
                reports {
                    xml.required.set(true)
                    html.required.set(true)
                }

                val sources = variant.sources
                sourceDirectories.setFrom(files(sources.kotlin?.all, sources.java?.all))

                // TODO: Use a proper API when https://issuetracker.google.com/332830826 is fixed
                executionData.setFrom(
                    project.fileTree("$buildDir/outputs/unit_test_code_coverage/${variant.name}UnitTest")
                        .matching { include("**/*.exec") },

                    project.fileTree("$buildDir/outputs/code_coverage/${variant.name}AndroidTest")
                        .matching { include("**/*.ec") }
                )
            }

        variant.artifacts.forScope(ScopedArtifacts.Scope.PROJECT)
            .use(reportTask)
            .toGet(
                ScopedArtifact.CLASSES,
                { _ -> allJars },
                { _ -> allDirectories },
            )
    }
}

fun String.capitalize() = replaceFirstChar {
    if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
}
