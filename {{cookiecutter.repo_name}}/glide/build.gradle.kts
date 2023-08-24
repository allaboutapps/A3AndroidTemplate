plugins {
    BuildPlugin
    com.android.library
    `kotlin-android`
    `kotlin-kapt`
}

dependencies {
    // expose Glide as api since we may need to use transformers etc
    api(libs.glide)
    kapt(libs.glide.compiler)

    kapt(libs.androidx.annotations)

    implementation(libs.androidx.appCompat)
}
