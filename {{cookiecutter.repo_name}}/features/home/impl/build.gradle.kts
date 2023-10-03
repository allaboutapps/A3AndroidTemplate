plugins {
    BuildPlugin
    com.android.library
    `kotlin-android`
    `kotlin-kapt`
}

android {
    namespace = "{{ cookiecutter.package_name }}.features.home.impl"
}

dependencies {
    implementation(project(":features:home:public"))
}
