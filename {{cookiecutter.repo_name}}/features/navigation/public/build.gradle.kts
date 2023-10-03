plugins {
    BuildPlugin
    com.android.library
    kotlin("android")
}

android {
    namespace = "{{ cookiecutter.package_name }}.features.navigation"
}
dependencies {
    implementation(libs.dagger)

    implementation(libs.androidx.navigation.compose)
    implementation(platform(libs.androidx.compose.platform))
}
