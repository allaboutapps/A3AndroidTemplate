plugins {
    BuildPlugin
    com.android.library
    `kotlin-android`
    `kotlin-kapt`
}

android {
    namespace = "{{ cookiecutter.package_name }}.features.navigation.impl"

    buildFeatures {
        compose = true
        buildConfig = false
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
}

dependencies {
    implementation(project(":features:navigation:public"))

    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    implementation(libs.core.ktx)
    // Navigation
    implementation(libs.androidx.navigation.compose)
    implementation(platform(libs.androidx.compose.platform))

    implementation(libs.dagger)
    implementation(libs.dagger.android)
    kapt(libs.dagger.compiler.hilt)
}
