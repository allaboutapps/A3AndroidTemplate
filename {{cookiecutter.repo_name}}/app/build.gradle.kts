plugins {
    BuildPlugin
    com.android.application
    id("com.google.android.gms.oss-licenses-plugin")
    id("at.allaboutapps.gradle-plugin")
    `kotlin-android`
    `kotlin-kapt`
    id("androidx.navigation.safeargs.kotlin")
    `kotlin-parcelize`{% if cookiecutter.firebase_messaging == "yes" or cookiecutter.firebase_crashlytics == "yes" or cookiecutter.firebase_analytics == "yes" %}
    id("com.google.gms.google-services"){% endif %}{% if cookiecutter.firebase_crashlytics == "yes" %}
    id("com.google.firebase.crashlytics"){% endif %}
}

android {
    defaultConfig {
        applicationId = "{{cookiecutter.package_name}}"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        multiDexEnabled = true
        vectorDrawables.useSupportLibrary = false // only for < API 21

        addManifestPlaceholders(mapOf("apiKey" to "secret")) // use with ${apiKey} in manifest

        resConfigs("de") // todo specify default locale(s)
    }

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        // Flag to enable support for the new language APIs
        // https://developer.android.com/studio/write/java8-support
        isCoreLibraryDesugaringEnabled = true
    }

    buildTypes {
        getByName("debug") {
            // Speed up debug builds
            aaptOptions.cruncherEnabled = false
            // prevent crashlytics from updating its id
            ext["alwaysUpdateBuildId"] = false
        }
    }

    flavorDimensions("environment")

    productFlavors {
        create("development") {
            dimension = "environment"
            ext["neverBuildRelease"] = true

            buildConfigField("String", "SERVER_API_URL", "\"https://debug.example.com/\"")
        }
        create("staging") {
            dimension = "environment"
            ext["neverBuildRelease"] = true

            buildConfigField("String", "SERVER_API_URL", "\"https://staging.example.com/\"")
        }
        create("live") {
            dimension = "environment"

            buildConfigField("String", "SERVER_API_URL", "\"https://www.example.com/\"")
        }
    }

    packagingOptions {
        exclude("LICENSE.txt")
        exclude("META-INF/LICENSE.txt")
        exclude("META-INF/NOTICE.txt")
        exclude("META-INF/ASL2.0")
        exclude("META-INF/DEPENDENCIES")
        exclude("META-INF/LICENSE")
        exclude("META-INF/NOTICE")
    }

    dexOptions {
        javaMaxHeapSize = "4g"
        preDexLibraries = true
    }
}

dependencies {
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:1.1.5")

    implementation(project(":networking"))
    implementation(project(":unwrapretrofit"))
    implementation(project(":glide"))

    implementation(Dependencies.KotlinStdLib)
    implementation(Dependencies.MaterialComponents)
{% if cookiecutter.firebase_crashlytics == "yes" or cookiecutter.firebase_messaging == "yes" or cookiecutter.firebase_analytics == "yes" %}
    // Firebase Libs{% if cookiecutter.firebase_crashlytics == "yes" %}
    implementation(Dependencies.FirebaseCrashlytics){% endif %}{% if cookiecutter.firebase_messaging == "yes" %}
    implementation(Dependencies.FirebaseMessaging){% endif %}{% if cookiecutter.firebase_analytics == "yes" %}
    implementation(Dependencies.FirebaseAnalytics){% endif %}
{% endif %}
    // ViewBinding helper
    implementation(Dependencies.ViewBindingDelegate)

    // aaa libs
    implementation(Dependencies.A3Utilities)
    implementation(Dependencies.A3RecyclerViewDecorations)

    // Android Kotlin Extensions by Google
    // https://developer.android.com/kotlin/ktx
    implementation(Dependencies.AndroidXCoreKtx)
    implementation(Dependencies.AndroidXPreferenceManager)

    // Support library depends on this lightweight import
    implementation(Dependencies.AndroidXLifecycleViewModel)
    implementation(Dependencies.AndroidXLifecycleLiveData)

    kapt(Dependencies.AndroidXLifecycleCompiler)

    // optional - ReactiveStreams support for LiveData
    implementation(Dependencies.AndroidXLifecycleReactiveStreams)

    // Play Services Licenses Lib
    implementation(Dependencies.PlayServicesLicenses)

    // logging
    implementation(Dependencies.Timber)

    implementation(Dependencies.RxJava3)
    implementation(Dependencies.RxKotlin3)

    // networking
    implementation(Dependencies.RetrofitAdapterRxJava3)
    implementation(Dependencies.RetrofitConverterMoshi)

    implementation(Dependencies.OkHttpLoggingInterceptor)
    implementation(Dependencies.Moshi)
    implementation(Dependencies.MoshiAdapters)

    // dependency injection
    implementation(Dependencies.Dagger)
    implementation(Dependencies.DaggerAndroid)
    implementation(Dependencies.DaggerAndroidSupport)
    kapt(Dependencies.DaggerCompiler)
    kapt(Dependencies.DaggerCompilerAndroid)

    // Leak Canary
    // debugImplementation because LeakCanary should only run in debug builds.
    debugImplementation(Dependencies.LeakCanary)

    implementation(Dependencies.AndroidXNavigationFragment)
    implementation(Dependencies.AndroidXNavigationUI)
}{% if cookiecutter.firebase_messaging == "yes" or cookiecutter.firebase_analytics == "yes"  %}

plugins.apply("com.google.gms.google-services"){% endif %}{% if cookiecutter.string_tool == "texterify" or cookiecutter.string_tool == "googlesheet" %}

fun osIsWindows(): Boolean {
    return System.getProperty("os.name").contains("windows", ignoreCase = true)
}{% endif %}{% if cookiecutter.string_tool == "texterify" %}

tasks.register("updateStrings", Exec::class) {
    group = "localization"

    val executableName =
        if (osIsWindows()) {
            listOf("cmd", "/c", "texterify")
        } else {
            listOf("texterify")
        }

    commandLine = (executableName + "download")
}{% endif %}{% if cookiecutter.string_tool == "googlesheet" %}

tasks.register("updateStrings", Exec::class) {
    group = "localization"

    val executableName =
        if (osIsWindows()) {
            listOf("cmd", "/c", "google-docs-i18n-strings")
        } else {
            listOf("google-docs-i18n-strings")
        }

    val args =
        listOf(
            "-a", "src/main/res",
            "-p", "android",
            "-s", "{{cookiecutter.strings_sheet_id}}"
        )

    commandLine = (executableName + args)
}{% endif %}
