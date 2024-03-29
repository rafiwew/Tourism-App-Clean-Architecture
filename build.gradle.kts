// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.10" apply false
    id("com.google.devtools.ksp") version "1.9.10-1.0.13" apply false
}

buildscript {
    extra.set("rxjavaVersion", "2.2.19")
    extra.set("rxandroidVersion", "2.1.1")
    extra.set("lifecycleVersion", "2.7.0")
}