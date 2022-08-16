// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        mavenCentral()
        maven("https://plugins.gradle.org/m2/")
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.2.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.Kotlin}")
        classpath("at.allaboutapps.gradle:plugin:3.0.11")
        classpath("com.google.android.gms:oss-licenses-plugin:0.10.5")
        classpath(Dependencies.AndroidXNavigationSafeArgs)
        {% if cookiecutter.firebase_messaging == "yes" or cookiecutter.firebase_crashlytics == "yes" or cookiecutter.firebase_analytics == "yes" %}classpath("com.google.gms:google-services:4.3.3"){% endif %}
        {% if cookiecutter.firebase_crashlytics == "yes" %}classpath("com.google.firebase:firebase-crashlytics-gradle:2.2.1"){% endif %}
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

if (!project.hasProperty("devBuild")) {
    // flag used for some build performance improvements
    // add `devBuild=true` to your global gradle.properties to use
    project.ext["devBuild"] = false
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
