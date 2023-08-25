plugins {
    BuildPlugin
    com.android.library
    `kotlin-android`
    `kotlin-kapt`
}

android {
    namespace = "at.aaa.one.config"
}

dependencies {
    implementation(project(":networking"))

    // Dagger
    implementation(libs.dagger)

    implementation(libs.rx.java3)
    implementation(libs.rx.relay3)
}
