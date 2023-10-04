plugins {
    BuildPlugin
    com.android.library
    `kotlin-android`
    `kotlin-kapt`
}

android {
    namespace = "{{ cookiecutter.package_name }}.config"
}

dependencies {
    implementation(project(":common:networking"))

    // Dagger
    implementation(libs.dagger)

    implementation(libs.rx.java3)
    implementation(libs.rx.relay3)
}
