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
        repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}

fun feature(path: String) = listOf(":features$path:impl", ":features$path:public")
fun screen(path: String) = listOf(":screens$path:impl", ":screens$path:public")

include(":app")
include(":common:networking")
include(":common:envelope")
include(":common:config")

include(":common:navigation")
include(":common:theme")

include(feature(":home"))
include(feature(":navigation"))

include(screen(":about"))
include(screen(":home"))
