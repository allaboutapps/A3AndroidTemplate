// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    // removed versions because of buildSrc, see https://github.com/gradle/gradle/issues/20084
    id(libs.plugins.android.application.get().pluginId).apply(false)
    id(libs.plugins.android.library.get().pluginId).apply(false)
    id(libs.plugins.kotlin.android.get().pluginId).apply(false)

    alias(libs.plugins.a3).apply(false)
    alias(libs.plugins.google.ossLicenses).apply(false)
    alias(libs.plugins.androidx.navigation).apply(false)
    alias(libs.plugins.ktlint).apply(false)
    alias(libs.plugins.hilt.android).apply(false)

    {%- if cookiecutter.firebase_analytics == "yes" or cookiecutter.firebase_messaging == "yes" or cookiecutter.firebase_crashlytics == "yes" %}
    alias(libs.plugins.google.services).apply(false)
    {%- endif %}
    {%- if cookiecutter.firebase_crashlytics == "yes" %}
    alias(libs.plugins.firebase.crashlytics).apply(false)
    {%- endif %}
}

if (!project.hasProperty("devBuild")) {
    // flag used for some build performance improvements
    // add `devBuild=true` to your global gradle.properties to use
    project.ext["devBuild"] = false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
