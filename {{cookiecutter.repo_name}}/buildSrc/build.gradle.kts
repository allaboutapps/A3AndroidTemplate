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
    val kotlinVersion = "1.5.31"

    implementation("com.android.tools.build:gradle:4.1.1")

    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    implementation("org.jetbrains.kotlin:kotlin-annotation-processing-gradle:$kotlinVersion")
    implementation(gradleApi())
}
