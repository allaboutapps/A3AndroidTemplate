plugins {
    BuildPlugin
    com.android.library
    `kotlin-android`
}

android {
    namespace = "{{ cookiecutter.package_name }}.screens.homescreen"
}

dependencies {
    implementation(libs.androidx.navigation.compose)
}
