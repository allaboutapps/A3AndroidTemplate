plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation(gradleApi())
    implementation(libs.gradleplugin.android)
    implementation(libs.gradleplugin.kotlin)
}
