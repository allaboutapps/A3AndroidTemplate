plugins {
    BuildPlugin
    com.android.library
    `kotlin-android`
}

android {
    namespace = "{{ cookiecutter.package_name }}.common.theme"

    buildFeatures {
        compose = true
        buildConfig = false
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }

}
dependencies {
    implementation(libs.androidx.navigation.compose)
    implementation(platform(libs.androidx.compose.platform))

    // Material Design 3
    implementation(libs.androidx.compose.material3)
}
