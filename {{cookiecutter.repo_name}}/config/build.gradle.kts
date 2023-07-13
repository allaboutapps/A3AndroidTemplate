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

idea {
    module {
        sourceDirs.addAll(files("build/generated/source/kapt/main", "build/generated/source/kaptKotlin/main"))
        generatedSourceDirs.addAll(files("build/generated/source/kapt/main", "build/generated/source/kaptKotlin/main"))
    }
}
