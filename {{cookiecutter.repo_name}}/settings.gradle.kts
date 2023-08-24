rootProject.name = "{{cookiecutter.repo_name}}"

pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id == "com.google.android.gms.oss-licenses-plugin") {
                useModule("com.google.android.gms:oss-licenses-plugin:${requested.version}")
            }
            if (requested.id.id == "at.allaboutapps.gradle-plugin") {
                useModule("at.allaboutapps.gradle:plugin:${requested.version}")
            }
        }
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}

include(":app")
include(":networking")
include(":glide")
include(":unwrapretrofit")
include(":envelope")
include(":config")
