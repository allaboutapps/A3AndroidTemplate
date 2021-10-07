plugins {
    BuildPlugin
    com.android.library
    `kotlin-android`
    `kotlin-kapt`
}

dependencies {
    // expose Glide as api since we may need to use transformers etc
    api("com.github.bumptech.glide:glide:${Versions.Glide}")
    kapt("com.github.bumptech.glide:compiler:${Versions.Glide}")

    kapt(Dependencies.AndroidXAnnotations)

    implementation(Dependencies.AndroidXAppCompat)
    implementation(Dependencies.KotlinStdLib)
}
