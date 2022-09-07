plugins {
    BuildPlugin
    com.android.library
    `kotlin-android`
    `kotlin-kapt`
}

android {
    buildFeatures {
        viewBinding = true
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {
    implementation(Dependencies.Epoxy)
    kapt(Dependencies.EpoxyKapt)

    implementation(Dependencies.AndroidXRecyclerView)
    implementation(Dependencies.MaterialComponents)
    implementation(Dependencies.KotlinStdLib)
}
