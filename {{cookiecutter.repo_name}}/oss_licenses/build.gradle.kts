plugins {
    BuildPlugin
    com.android.library
    `kotlin-android`
    `kotlin-parcelize`
}

android {
    buildFeatures {
        viewBinding = true
    }

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(Dependencies.KotlinStdLib)
    implementation(Dependencies.AndroidXAppCompat)
    implementation(Dependencies.AndroidXCoreKtx)
    implementation(Dependencies.A3WebView)
}
