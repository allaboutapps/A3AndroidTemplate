plugins {
    BuildPlugin
    com.android.library
    `kotlin-android`
}

android {
    namespace = "{{ cookiecutter.package_name }}.screens.about"
}

dependencies {
    implementation(libs.androidx.navigation.compose)
}
