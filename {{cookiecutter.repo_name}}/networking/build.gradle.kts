plugins {
    BuildPlugin
    kotlin
    `kotlin-kapt`
}

dependencies {
    implementation(project(":unwrapretrofit"))
    implementation(project(":envelope"))

    implementation(libs.okhttp)
    implementation(libs.retrofit)
    implementation(libs.moshi)
    kapt(libs.moshi.codegen)

    implementation(libs.rx.java3)
}
