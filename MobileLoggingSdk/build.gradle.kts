// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.android.library) apply false
}

buildscript {
    dependencies {
        classpath("com.android.tools.build:gradle:8.5.1")
    }
}

allprojects {
    group = "com.ivoafsilva.mobiweb.mobilesdk"
    version = "1.0.0-alpha"
}