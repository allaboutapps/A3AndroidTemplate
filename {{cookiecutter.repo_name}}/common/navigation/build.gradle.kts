plugins {
    BuildPlugin
    com.android.library
    kotlin("android")
}

android {
    namespace = "{{ cookiecutter.package_name }}.common.navigation"
}
dependencies {
    implementation(libs.androidx.navigation.compose)
    implementation(platform(libs.androidx.compose.platform))
}
