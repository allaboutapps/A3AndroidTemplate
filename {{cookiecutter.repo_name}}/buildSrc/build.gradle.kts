plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}

sourceSets {
    main.get().java.srcDirs += file("../benchmark/gradle-plugin/src/main/kotlin")
}

dependencies {
    val kotlinVersion = "1.7.10"

    implementation("com.android.tools.build:gradle:7.2.1")

    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    implementation("org.jetbrains.kotlin:kotlin-annotation-processing-gradle:$kotlinVersion")
    implementation(gradleApi())
}
