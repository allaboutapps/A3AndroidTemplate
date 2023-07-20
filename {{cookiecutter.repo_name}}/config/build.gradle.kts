plugins {
    BuildPlugin
    com.android.library
    `kotlin-android`
    `kotlin-kapt`
    idea
}

dependencies {
    implementation(project(":networking"))

    implementation(Dependencies.Dagger)

    implementation(Dependencies.RxJava3)
    implementation(Dependencies.RxRelay)
}
