plugins {
    BuildPlugin
    com.android.application
    `kotlin-android`
    `kotlin-kapt`
    `kotlin-parcelize`
    id(libs.plugins.a3.get().pluginId)
    id(libs.plugins.ktlint.get().pluginId)
    id(libs.plugins.google.ossLicenses.get().pluginId)
    id(libs.plugins.androidx.navigation.get().pluginId)
    {%- if cookiecutter.firebase_analytics == "yes" or cookiecutter.firebase_messaging == "yes" %}
    id(libs.plugins.google.services.get().pluginId)
    {%- endif %}
    {%- if cookiecutter.firebase_crashlytics == "yes" %}
    id(libs.plugins.firebase.crashlytics.get().pluginId)
    {%- endif %}
}

ktlint {
    version.set("0.50.0")
    installGitPreCommitHookBeforeBuild.set(true)
}

android {
    namespace = "{{cookiecutter.package_name}}"

    defaultConfig {
        applicationId = "{{cookiecutter.package_name}}"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        multiDexEnabled = true
        vectorDrawables.useSupportLibrary = false // only for < API 21

        addManifestPlaceholders(mapOf("apiKey" to "secret")) // use with ${apiKey} in manifest

        buildConfigField(
            "String",
            "URL_CONFIG",
            "\"https://public.allaboutapps.at/config/{{ cookiecutter.repo_name }}/version.json\"",
        )

        resConfigs("de") // todo specify default locale(s)
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
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

    flavorDimensions.add("environment")

    productFlavors {
        create("development") {
            dimension = "environment"
            ext["neverBuildRelease"] = true
            applicationIdSuffix = ".development"
            buildConfigField("String", "SERVER_API_URL", "\"https://debug.example.com/\"")
        }
        create("staging") {
            dimension = "environment"
            ext["neverBuildRelease"] = true
            applicationIdSuffix = ".staging"
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
}

dependencies {
    coreLibraryDesugaring(libs.android.coreLibDesugaring)

    implementation(project(":networking"))
    implementation(project(":unwrapretrofit"))
    implementation(project(":envelope"))
    implementation(project(":glide"))
    implementation(project(":config"))

    implementation(libs.androidx.core.coreKtx)
    implementation(libs.androidx.core.splash)
    implementation(libs.androidx.preferenceKtx)
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)
    implementation(libs.android.material)

    // Lifecycle
    implementation(libs.androidx.lifecycle.viewmodelKtx)
    implementation(libs.androidx.lifecycle.livedataKtx)
    implementation(libs.androidx.lifecycle.reactivestreamsKtx)
    kapt(libs.androidx.lifecycle.compiler)

    // Dagger
    implementation(libs.dagger)
    implementation(libs.dagger.android)
    implementation(libs.dagger.android.support)
    kapt(libs.dagger.compiler)
    kapt(libs.dagger.compiler.android)

    // Firebase
    implementation(platform(libs.firebase.platform))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics)

    // Rx
    implementation(libs.rx.java3)
    implementation(libs.rx.kotlin3)
    implementation(libs.rx.android3)

    // A3
    implementation(libs.a3.utilities)
    implementation(libs.a3.recyclerViewDecorations)

    // ViewBinding Delegate
    implementation(libs.fragmentViewBindingDelegate)

    // Play Services Licenses
    implementation(libs.android.playServicesOssLicenses)

    // Play App Update
    implementation(libs.android.playAppUpdateKtx)

    // Logging
    implementation(libs.timber)

    // Networking
    implementation(libs.retrofit)
    implementation(libs.retrofit.adapter.rx3)
    implementation(libs.retrofit.converter.moshi)
    implementation(libs.okhttp.interceptor.logging)
    implementation(libs.moshi)
    implementation(libs.moshi.adapters)

    debugImplementation(libs.leakCanary)
}

{%- if cookiecutter.string_tool == "texterify" %}
fun osIsWindows(): Boolean {
    return System.getProperty("os.name").contains("windows", ignoreCase = true)
}

tasks.register("updateStrings", Exec::class) {
    group = "localization"

    val executableName =
        if (osIsWindows()) {
            listOf("cmd", "/c", "texterify")
        } else {
            listOf("texterify")
        }

    commandLine = (executableName + "download")
}
{%- endif %}
