plugins {
    BuildPlugin
    ScreenComposePlugin
    com.android.library
    `kotlin-android`
    `kotlin-kapt`
}

android {
    namespace = "{{ cookiecutter.package_name }}.screens.homescreen.impl"
}

dependencies {
    implementation(project(":screens:homescreen:public"))

    implementation(project(":common:navigation"))
    implementation(project(":features:navigation:public"))

    implementation(project(":screens:about:public"))
}
